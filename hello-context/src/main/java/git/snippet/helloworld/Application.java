package git.snippet.helloworld;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class Application {
    private BeanFactory beanFactory;

    public Application() {
        beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((DefaultListableBeanFactory) beanFactory);
        reader.loadBeanDefinitions("beans.xml");
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.sayHello();
    }

    public void sayHello() {
        Hello hello = (Hello) beanFactory.getBean("hello");
        // Hello hello = beanFactory.getBean("hello", Hello.class);
        System.out.println(hello.hello());

    }


}
