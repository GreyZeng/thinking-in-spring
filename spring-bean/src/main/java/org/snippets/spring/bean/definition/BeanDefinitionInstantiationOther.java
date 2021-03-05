package org.snippets.spring.bean.definition;

import org.snippets.spring.bean.definition.factory.DefaultUserFactory;
import org.snippets.spring.bean.definition.factory.UserFactory;
import org.snippets.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ServiceLoader;

import static java.util.ServiceLoader.load;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/5
 * @since
 */
public class BeanDefinitionInstantiationOther {
    public static void main(String[] args) {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:/META-INF/other-bean-instantiation-context.xml");
        AutowireCapableBeanFactory beanFactory = factory.getAutowireCapableBeanFactory();
        ServiceLoader<UserFactory> userFactoryServiceLoader = factory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(userFactoryServiceLoader);

        DefaultUserFactory bean = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(bean.createUser());

    }

    public static void serviceLoaderBySPI() {
        ServiceLoader<UserFactory> load = load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(load);
    }

    public static void displayServiceLoader(ServiceLoader<UserFactory> load) {
        for (UserFactory next : load) {
            System.out.println(next.createUser());
        }
    }
}
