# Spring中的循环依赖问题

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

但是Spring创建对象由于有相对复杂的生命周期，所以可能会导致循环依赖的问题，我们将如上代码转换成使用Spring的方式,需要引入：

```xml

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.2.13.RELEASE</version>
</dependency>
```

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

