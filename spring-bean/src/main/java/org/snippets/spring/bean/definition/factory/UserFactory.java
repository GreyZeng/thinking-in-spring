package org.snippets.spring.bean.definition.factory;

import org.snippets.spring.ioc.overview.dependency.domain.User;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/5
 * @since
 */
public interface UserFactory {
    default User createUser() {
        return User.createUser();
    }
}
