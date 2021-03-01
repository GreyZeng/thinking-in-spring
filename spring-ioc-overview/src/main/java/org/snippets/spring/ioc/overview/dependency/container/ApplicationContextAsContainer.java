package org.snippets.spring.ioc.overview.dependency.container;

import org.snippets.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

public class ApplicationContextAsContainer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类配置为Configuration类
        applicationContext.register(ApplicationContextAsContainer.class);
        // 启动
        applicationContext.refresh();
        lookupByTypeCollection(applicationContext);
        // 关闭上下文
        applicationContext.close();
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(2L);
        user.setName("zhangsan");
        return user;
    }

    private static void lookupByTypeCollection(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory beanFactory1 = (ListableBeanFactory) beanFactory;
            Map<String, User> users = beanFactory1.getBeansOfType(User.class);
            System.out.println(users);
        }
    }
}
