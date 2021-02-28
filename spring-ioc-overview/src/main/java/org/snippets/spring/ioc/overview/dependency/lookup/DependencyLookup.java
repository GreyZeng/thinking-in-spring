package org.snippets.spring.ioc.overview.dependency.lookup;

import org.snippets.spring.ioc.overview.dependency.annotation.Super;
import org.snippets.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 通过名称查找
 */
public class DependencyLookup {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup.xml");
        lookupLazy(beanFactory);
        lookupRealtime(beanFactory);
        lookupByTypeSingle(beanFactory);
        lookupByTypeCollection(beanFactory);
        lookupByAnnotations(beanFactory);
    }

    private static void lookupByAnnotations(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory beanFactory1 = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map) beanFactory1.getBeansWithAnnotation(Super.class);
            System.out.println(users);
        }
    }

    private static void lookupByTypeSingle(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }

    private static void lookupByTypeCollection(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory beanFactory1 = (ListableBeanFactory) beanFactory;
            Map<String, User> users = beanFactory1.getBeansOfType(User.class);
            System.out.println(users);
        }
    }

    // 延迟查找（按Bean名称）
    private static void lookupLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println(user);
    }


    // 实时查找（按Bean名称）
    private static void lookupRealtime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println(user);
    }


}
