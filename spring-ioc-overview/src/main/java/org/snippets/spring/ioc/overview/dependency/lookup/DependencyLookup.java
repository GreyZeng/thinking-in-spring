package org.snippets.spring.ioc.overview.dependency.lookup;

import org.snippets.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyLookup {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup.xml");
        User user = (User)beanFactory.getBean("user");
        System.out.println(user);
    }
}
