package org.snippets.spring.bean.definition;

import org.snippets.spring.bean.definition.factory.DefaultUserFactory;
import org.snippets.spring.bean.definition.factory.UserFactory;
import org.snippets.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/5
 * @since
 */
public class BeanInitialDemo {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitialDemo.class);
        applicationContext.refresh();
        // 标记了@Lazy,这句话会先打印 ，如果Lazy = false 这句话会后打印
        // refresh()方法中
        // Instantiate all remaining (non-lazy-init) singletons.
        // finishBeanFactoryInitialization(beanFactory);
        System.out.println("对延迟加载和非延迟加载...");
        applicationContext.getBean(UserFactory.class);
        System.out.println("应用上下文准备关闭");
        applicationContext.close();
        System.out.println("应用上下文已经关闭");
    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "destroyUserFactory")
    @Lazy
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }

}
