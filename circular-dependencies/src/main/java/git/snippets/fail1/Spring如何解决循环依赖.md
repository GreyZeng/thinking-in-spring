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

```java
git.snippets.fail1.A@ed9d034
git.snippets.fail1.B@6121c9d6
```

以下是循环依赖是否支持的开关，如果设置为true，则支持循环依赖。

```java
// 是否支持循环依赖
applicationContext.setAllowCircularReferences(true);
```

如果设置为false，再次运行main方法，则报错，以下为简略报错信息：

```java
nested exception is org.springframework.beans.factory.BeanCurrentlyInCreationException:Error creating bean with name'x':
        Requested bean is currently in creation:Is there an unresolvable circular reference?
```

通过上述实验，可以了解，Spring解决了循环依赖的问题，如何解决的呢？我们需要先看下Spring中Bean的生命周期：

以x的创建为例：

1. 解析XML或者注解，将信息注册到BeanDefinition中
2. 对象的实例化，可以理解成 X x = new X();
3. x的属性填充(这里就涉及到要填充Y的实例)
4. x的初始化
5. Bean后置处理器进行处理（比如AOP）
6. 把Bean添加到单例池中

参考文档

https://www.jianshu.com/p/1dec08d290c1
https://www.cnblogs.com/zrtqsk/p/3735273.html
https://www.cnblogs.com/javazhiyin/p/10905294.html
https://blog.csdn.net/kznsbs/article/details/109394582