package git.snippets.fail1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


public class CircularDependenciesDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类配置为Configuration类
        applicationContext.register(CircularDependenciesDemo.class);
        // 是否支持循环依赖
        applicationContext.setAllowCircularReferences(true);
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
