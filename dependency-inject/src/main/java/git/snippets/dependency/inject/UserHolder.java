package git.snippets.dependency.inject;

import org.snippets.spring.ioc.overview.dependency.domain.User;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/25
 * @since
 */
public class UserHolder {
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public UserHolder() {

    }

    private User user;

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
