package git.snippets.dependency.inject;

import org.snippets.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/26
 * @since
 */
public class QualifierAnnotationDependencyInjection {
    @Autowired
    private User user;
    @Autowired
    @Qualifier("user")
    private User user2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        beanFactory.register(QualifierAnnotationDependencyInjection.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        beanFactory.refresh();
        QualifierAnnotationDependencyInjection bean = beanFactory.getBean(QualifierAnnotationDependencyInjection.class);
        System.out.println(bean.user);
        System.out.println(bean.user2);
        beanFactory.close();
    }
}
