package org.snippets.spring.ioc.overview.dependency.injection;

import org.snippets.spring.ioc.overview.dependency.repo.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

public class DependencyInjection {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection.xml");
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        //System.out.println(userRepository.getUsers());
        // 依赖注入
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory() == beanFactory);

        // 依赖查找
        // BeanFactory不是内建的Bean对象
        // System.out.println(beanFactory.getBean(BeanFactory.class));


        System.out.println(userRepository.getObjectFactory().getObject() == beanFactory);

        // 容器内建的Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("Environment bean" + environment);

    }
}
