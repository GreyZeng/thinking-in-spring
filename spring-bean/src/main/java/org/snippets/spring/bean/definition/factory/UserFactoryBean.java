package org.snippets.spring.bean.definition.factory;

import org.snippets.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/5
 * @since
 */
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
