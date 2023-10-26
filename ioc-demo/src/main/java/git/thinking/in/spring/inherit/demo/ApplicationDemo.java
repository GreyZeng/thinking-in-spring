package git.thinking.in.spring.inherit.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationDemo {
    private ApplicationContext beanFactory;

    public static void main(String[] args) {
        ApplicationDemo application = new ApplicationDemo();
        application.sayHello();
    }

    public ApplicationDemo() {
        beanFactory = new ClassPathXmlApplicationContext("beans.xml");
    }

    public void sayHello() {
//        Hello hello = (Hello) beanFactory.getBean("hello");
        Hello hello = beanFactory.getBean("hello", Hello.class);
        System.out.println(hello.hello());
    }
}
