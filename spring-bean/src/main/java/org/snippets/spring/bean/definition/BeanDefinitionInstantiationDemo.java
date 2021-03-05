package org.snippets.spring.bean.definition;

import org.snippets.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/5
 * @since
 */
public class BeanDefinitionInstantiationDemo {
    public static void main(String[] args) {

// 1. 通过静态方法
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        User user = beanFactory.getBean("user-by-static", User.class);
        System.out.println(user);
        // 2. 通过实例方法
        User user2 = beanFactory.getBean("user-by-instantiation", User.class);
        System.out.println(user2);
        System.out.println(user == user2);
        // 3. FactoryBean方法 实例化+初始化
        User user3 = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println(user3);
    }
}
