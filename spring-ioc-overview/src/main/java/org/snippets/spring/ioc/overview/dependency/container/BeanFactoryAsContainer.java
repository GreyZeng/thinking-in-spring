package org.snippets.spring.ioc.overview.dependency.container;

import org.snippets.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

public class BeanFactoryAsContainer {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        String location = "classpath:/META-INF/dependency-lookup.xml";
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
        System.out.println(beanDefinitionsCount);
        lookupByTypeCollection(beanFactory);
    }

    private static void lookupByTypeCollection(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory beanFactory1 = (ListableBeanFactory) beanFactory;
            Map<String, User> users = beanFactory1.getBeansOfType(User.class);
            System.out.println(users);
        }
    }
}
