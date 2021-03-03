package org.snippets.spring.bean.definition;

import org.snippets.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/3
 * @since
 */
public class BeanDefinitionAliasDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");
        User newUser = beanFactory.getBean("newUser", User.class);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(newUser == user);
    }
}
