package git.snippets.dependency.inject.annotaion;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/27
 * @since
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
public @interface MyAutorwired {
    boolean required() default true;
}
