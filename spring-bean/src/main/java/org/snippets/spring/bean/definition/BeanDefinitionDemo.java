package org.snippets.spring.bean.definition;

import org.snippets.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/3
 * @since
 */
public class BeanDefinitionDemo {
    public static void main(String[] args) {
        // 通过BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 2l).addPropertyValue("name", "张三");
        // 获取实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // 并非Bean的最终状态，可以自定义修改

        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.add("id", 3l).add("name", "lisi");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);


    }
}
