package git.thinking.in.spring.inherit.demo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class BeanFactoryDemo {
    private BeanFactory beanFactory;

    public static void main(String[] args) {
//        Hello hello = new Hello();
//        System.out.println(hello.hello());
        BeanFactoryDemo application = new BeanFactoryDemo();
        application.sayHello();
    }

    public BeanFactoryDemo() {
        beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((DefaultListableBeanFactory) beanFactory);
        reader.loadBeanDefinitions("beans.xml");
    }

    public void sayHello() {
//        Hello hello = (Hello) beanFactory.getBean("hello");
        Hello hello = beanFactory.getBean("hello", Hello.class);
        System.out.println(hello.hello());
    }
}
