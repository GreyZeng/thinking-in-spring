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

import git.snippets.dependency.inject.annotaion.InjectUser;
import git.snippets.dependency.inject.annotaion.MyAutorwired;
import org.snippets.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;


/**
 * 延迟加载
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class LazyAnnotationDependencyInjectionDemo {
    @Autowired
    private User user;
    @Autowired
    private ObjectProvider<User> objectProvider;
    @Inject
    private User injectUser;
    @MyAutorwired
    private Optional<User> userOptional;
    @Autowired
    private ObjectProvider<Set<User>> objectProvider2;
    @InjectUser
    private User user3;


    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        Set<Class<? extends Annotation>> an =
                new LinkedHashSet<>(asList(Autowired.class, Inject.class, InjectUser.class));
        beanPostProcessor.setAutowiredAnnotationTypes(an);
        return beanPostProcessor;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        beanFactory.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        beanFactory.refresh();
        LazyAnnotationDependencyInjectionDemo bean = beanFactory.getBean(LazyAnnotationDependencyInjectionDemo.class);
        System.out.println(bean.user);
        System.out.println(bean.user3);
        System.out.println(bean.injectUser);
        System.out.println(bean.userOptional);
        System.out.println(bean.objectProvider.getObject());
        bean.objectProvider.forEach(System.out::println);
        System.out.println(bean.objectProvider2.getObject());
        beanFactory.close();

    }
}
