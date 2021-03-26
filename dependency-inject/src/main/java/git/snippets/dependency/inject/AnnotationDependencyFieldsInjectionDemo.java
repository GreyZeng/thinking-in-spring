/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package git.snippets.dependency.inject;

import org.snippets.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于 XML 资源的依赖 Constructor方式注入示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class AnnotationDependencyFieldsInjectionDemo {
    @Autowired // @Autowired注解会忽略掉static类型的字段
    private UserHolder userHolder;


    @Resource
    private UserHolder userHolder2;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        beanFactory.register(AnnotationDependencyFieldsInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        beanFactory.refresh();
        AnnotationDependencyFieldsInjectionDemo bean = beanFactory.getBean(AnnotationDependencyFieldsInjectionDemo.class);
        System.out.println(bean.userHolder);
        System.out.println(bean.userHolder == bean.userHolder2);
        beanFactory.close();

    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
