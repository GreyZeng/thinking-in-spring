package org.snippets.spring.bean.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/13
 * @since
 */
public class NoUniqueBeanDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 TypeSafetyDependencyLookupDemo 作为配置类（Configuration Class）
        applicationContext.register(NoUniqueBeanDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        try {
            applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err.printf("当前应用上下文有%d个%s类型的bean,具体原因：%s\n", e.getNumberOfBeansFound(), String.class, e.getMessage());
        }
        applicationContext.close();
    }

    @Bean
    //@Primary
    public String bean1() {
        return "bean1";
    }

    @Bean
    public String bean2() {
        return "bean2";
    }

    @Bean
    public String bean3() {
        return "bean3";
    }
}
