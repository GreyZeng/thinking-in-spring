package org.snippets.spring.bean.definition;

import org.snippets.spring.bean.definition.factory.DefaultUserFactory;
import org.snippets.spring.bean.definition.factory.UserFactory;
import org.snippets.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

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
        // 通过Java API方式来注册
        registerBeanDefinition(applicationContext, "xx-user");
        registerBeanDefinition(applicationContext);
        applicationContext.refresh();
        System.out.println("configs :" + applicationContext.getBeansOfType(Config.class));
        System.out.println("user :" + applicationContext.getBeansOfType(User.class));


        // 通过注册外部单例对象
        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerSingleton("uF", userFactory);

        UserFactory uF = applicationContext.getBean("uF", UserFactory.class);
        System.out.println(uF == userFactory);


        applicationContext.close();

    }

    public static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 33L).addPropertyValue("name", "zhaoliu");
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    public static void registerBeanDefinition(BeanDefinitionRegistry registry) {
        registerBeanDefinition(registry, null);
    }

    @Component
    public static class Config {
        @Bean(name = {"user", "userAlias"})
        public User user() {
            User user = new User();
            user.setName("wangwu");
            user.setId(3L);
            return user;
        }
    }
}
