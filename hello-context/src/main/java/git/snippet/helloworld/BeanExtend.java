package git.snippet.helloworld;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 的继承关系
 */
public class BeanExtend {
    private final ClassPathXmlApplicationContext parentContext;
    private final ClassPathXmlApplicationContext childContext;

    public static void main(String[] args) {
        new BeanExtend(true).runTests();
        new BeanExtend(false).runTests();
    }

    public BeanExtend(boolean open) {
        parentContext = new ClassPathXmlApplicationContext("parent-beans.xml");
        childContext = new ClassPathXmlApplicationContext(new String[]{"child-beans.xml"}, true, parentContext);
        parentContext.setId("ParentContext");
        childContext.setId("ChildContext");
        parentContext.setAllowBeanDefinitionOverriding(open);
        childContext.setAllowBeanDefinitionOverriding(open);

    }

    public void runTests() {
        testVisible(parentContext, "parentHello");
        testVisible(childContext, "parentHello");
        testVisible(parentContext, "childHello");
        testVisible(childContext, "childHello");
        testOverride(parentContext, "hello");
        testOverride(childContext, "hello");
    }

    private void testVisible(ClassPathXmlApplicationContext context, String beanName) {
        System.out.println(context.getId() + " can see " + beanName + ": " + context.containsBean(beanName));
    }

    private void testOverride(ApplicationContext context, String beanName) {
        System.out.println("sayHello from " + context.getId() + ": " + context.getBean(beanName, Hello.class).hello());
    }
}
