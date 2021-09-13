# Spring如何解决循环依赖

如果X这个类依赖了Y，Y这个类依赖了X，就产生了循环依赖。在普通Java（非Spring框架下），这并不是一个问题。

参考如下示例代码：

```java
public class Demo {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.b = b;
        b.a = a;
        System.out.println(a);
        System.out.println(b);
    }
}

class A {
    B b;
}

class B {
    A a;
}
```

但是Spring创建对象由于有相对复杂的生命周期，所以可能会导致循环依赖的问题，我们将如上代码转换成使用Spring的方式：

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class CircularDependenciesDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类配置为Configuration类
        applicationContext.register(CircularDependenciesDemo.class);
        // 是否支持循环依赖
        // applicationContext.setAllowCircularReferences(true);
        // 启动
        applicationContext.refresh();
        System.out.println(applicationContext.getBean("a"));
        System.out.println(applicationContext.getBean("b"));
        // 关闭上下文
        applicationContext.close();
    }

    @Bean
    public static A a() {
        return new A();
    }

    @Bean
    public static B b() {
        return new B();
    }
}

class A {
    @Autowired
    B b;
}

class B {
    @Autowired
    A a;
}
```

运行，正常打印出：

```text
git.snippets.fail1.A@ed9d034
git.snippets.fail1.B@6121c9d6
```

以下是循环依赖是否支持的开关，如果设置为true，则支持循环依赖。

```text
// 是否支持循环依赖
applicationContext.setAllowCircularReferences(true);
```

如果设置为false，再次运行main方法，则报错，以下为简略报错信息：

```text
nested exception is org.springframework.beans.factory.BeanCurrentlyInCreationException:Error creating bean with name'x':
        Requested bean is currently in creation:Is there an unresolvable circular reference?
```

通过上述实验，可以了解，Spring解决了循环依赖的问题，如何解决的呢？我们需要先看下Spring中Bean的生命周期：

以x的创建为例：

X阶段：

1. 解析XML或者注解，将信息注册到BeanDefinition中
2. 对象的实例化，可以理解成 X x = new X();
3. x的属性填充(这里就涉及到要填充Y的实例)
4. x的初始化
5. Bean后置处理器进行处理（比如AOP）
6. 把Bean添加到单例池中

在进行到第3步的时候，Spring会从单例池中找Y对应的Bean对象，如果找不到，则会执行y对象的创建过程生命周期，y也会经历和x一样的生命周期：

Y阶段：

1. 解析XML或者注解，将信息注册到BeanDefinition中
2. 对象的实例化，可以理解成 Y y = new Y();
3. y的属性填充(这里就涉及到要填充x的实例)
4. y的初始化
5. Bean后置处理器进行处理（比如AOP）
6. 把Bean添加到单例池中

这里执行到第三步的时候，需要找到x，但是x还没有进入单例池，所以，X阶段卡在第三步无法继续执行，Y阶段也卡在第三步无法继续执行，**这就导致了循环依赖问题。**

如何解决这个问题？

可以使用一个map，在X阶段第二步的时候，将new出来的x放入这个map中，这样一来，Y阶段的第3步涉及x的实例填充，就可以这样执行：

先从单例池中找x，找不到，**再从map中找x，此时因为X阶段的第2步已经把new出来的x放入到map中，所以可以从map中找到，填充即可。** 这样，Y阶段可以顺利执行完毕，然后X阶段正常结束

这样就解决了上面提到的循环依赖的问题。

参考文档

https://www.jianshu.com/p/1dec08d290c1
https://www.cnblogs.com/zrtqsk/p/3735273.html
https://www.cnblogs.com/javazhiyin/p/10905294.html
https://blog.csdn.net/kznsbs/article/details/109394582