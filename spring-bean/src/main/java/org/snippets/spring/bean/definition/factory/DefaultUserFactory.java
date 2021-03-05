package org.snippets.spring.bean.definition.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/5
 * @since
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct 初始化");
    }

    public void initUserFactory() {
        System.out.println("init 方法initUserFactory初始化");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean#afterPropertiesSet方法初始化");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy方式销毁");
    }

    public void destroyUserFactory() {
        System.out.println("destroy-method方法销毁");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("实现DisposableBean#destroy方法");
    }
}
