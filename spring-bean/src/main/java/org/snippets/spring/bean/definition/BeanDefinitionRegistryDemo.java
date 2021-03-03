package org.snippets.spring.bean.definition;

import org.snippets.spring.ioc.overview.dependency.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/3
 * @since
 */
@Import(BeanDefinitionRegistryDemo.Config.class)
public class BeanDefinitionRegistryDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanDefinitionRegistryDemo.class);
        applicationContext.refresh();
        System.out.println("configs :" + applicationContext.getBeansOfType(Config.class));
        System.out.println("user :" + applicationContext.getBeansOfType(User.class));
    }

    @Component
    public static class Config {
        @Bean(name = {"user", "userAlias"})
        public User user() {
            User user = new User();
            user.setName("wangwu");
            user.setId(3l);
            return user;
        }
    }
}
