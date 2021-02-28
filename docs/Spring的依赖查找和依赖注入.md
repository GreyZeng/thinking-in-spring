作者：[Grey](https://www.cnblogs.com/greyzeng/)

原文地址：

[语雀](https://www.yuque.com/greyzeng/uzfhep/oshvxb)

[博客园](https://www.cnblogs.com/greyzeng/p/14459565.html)

## 依赖查找

Spring IoC 依赖查找分为以下几种方式

- 根据 Bean 名称查找
    - 实时查找
    - 延迟查找

- 根据 Bean 类型查找
    - 单个 Bean 对象
    - 集合 Bean 对象

- 根据 Bean 名称 + 类型查找

- 根据 Java 注解查找
    - 单个 Bean 对象
    - 集合 Bean 对象

以下示例基于spring-framework 5.2.13.RELEASE 版本, 通过Maven管理项目

### 根据Bean名称实时查找

pom.xml 文件引入如下依赖

```xml

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
</dependency>
```

定义一个User作为Domain

```java
public class User {
    private Long id;
    private String name;
    // set / get / toString方法略
}

```

在resources目录下建立META—INF目录，同时新建一个dependency-lookup.xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

</beans>
```

在xml文件中配置如下内容

```xml
<?xml version="1.0"encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="user" class="org.snippets.spring.ioc.overview.dependency.domain.User">
        <property name="id" value="1"/>
        <property name="name" value="张三"/>
    </bean>

</beans>
```

新建测试类

```java
package org.snippets.spring.ioc.overview.dependency.lookup;

/**
 * 通过名称查找
 */
public class DependencyLookup {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup.xml");
        lookupRealtime(beanFactory);
    }

    // 实时查找（按Bean名称）
    private static void lookupRealtime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println(user);
    }
}

```

输出结果

```
User{id=1, name='张三'}
```

### 根据Bean名称延迟查找

在上例中的xml文件中配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="user" class="org.snippets.spring.ioc.overview.dependency.domain.User">
        <property name="id" value="1"/>
        <property name="name" value="张三"/>
    </bean>
    <bean id="objectFactory" class="org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean">
        <property name="targetBeanName" value="user"/>
    </bean>
</beans>
```

新建测试类

```java

public class DependencyLookup {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup.xml");
        lookupLazy(beanFactory);
    }

    // 延迟查找（按Bean名称）
    private static void lookupLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println(user);
    }
}

```

运行结果

```
User{id=1, name='张三'}
```

### 根据Bean类型查找单个对象

```java

private static void lookupByTypeSingle(BeanFactory beanFactory){
        User user=beanFactory.getBean(User.class);
        System.out.println(user);
        }

```

### 根据Bean类型查询集合对象

```java
private static void lookupByTypeCollection(BeanFactory beanFactory){
      if(beanFactory instanceof ListableBeanFactory){
        ListableBeanFactory beanFactory1=(ListableBeanFactory)beanFactory;
        Map<String, User> users=beanFactory1.getBeansOfType(User.class);
        System.out.println(users);
      }
}
```
### 根据Java注解来查询多个对象

首先我们定义一个注解@Super

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Super { }
```

然后我们定义一个User的子类SuperUser，并标注@Super注解

```java
@Super
public class SuperUser extends User {
    private String address;
    // set / get / toString方法忽略
}
```

我们在xml中做如下配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="user" class="org.snippets.spring.ioc.overview.dependency.domain.User">
        <property name="id" value="1"/>
        <property name="name" value="张三"/>
    </bean>
    <bean id="superUser" class="org.snippets.spring.ioc.overview.dependency.domain.SuperUser" parent="user"
          primary="true">
        <property name="address" value="广州"/>
    </bean>
    <bean id="objectFactory" class="org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean">
        <property name="targetBeanName" value="user"/>
    </bean>
</beans>
```

如果通过User.class类型来找Bean，可能会找到SuperUser和User两个，但是加了Primary="true"这个配置，则只会找superUser这个Bean

接下来就是通过注解来找到Bean的代码

```java
    private static void lookupByAnnotations(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory beanFactory1 = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map) beanFactory1.getBeansWithAnnotation(Super.class);
            System.out.println(users);
        }
    }
```

打印结果：

```
{superUser=SuperUser{address='广州'} User{id=1, name='张三'}}
```

## 依赖注入

Spring IoC 依赖注入分为以下几种方式

- 根据 Bean 名称注入

- 根据 Bean 类型注入
  - 单个 Bean 对象
  - 集合 Bean 对象

- 注入容器內建 Bean 对象

- 注入非 Bean 对象

- 注入类型
  - 实时注入
  - 延迟注入

增加UserRepository

```java

public class UserRepository {
    private Collection<User> users; // 自定义Bean
    private BeanFactory beanFactory; // 内建非 Bean(依赖）
    private ObjectFactory<ApplicationContext> objectFactory;
    // 省略 set/get/toString方法
}

```

首先我们新建一个配置文件dependency-injection.xml

引入上一例中的dependency-lookup.xml，并增加一个Bean的配置, 并且配置手动注入这个Bean中属性信息

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/util https://www.springframework.org/schema/util/spring-util.xsd">

    <import resource="dependency-lookup.xml"/>
    <bean id="userRepository"
          class="org.snippets.spring.ioc.overview.dependency.repo.UserRepository"
          > 
        <!-- 手动注入 -->
        <property name="users">
             <util:list>
                 <ref bean="user"/>
                 <ref bean="superUser"/>
             </util:list>
         </property>
    </bean>
</beans>
```

测试

```java
public class DependencyInjection {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection.xml");
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        System.out.println(userRepository.getUsers()); 
    }
}
```

可以打印出注入的user信息

也可以实现自动注入，我们以按类型自动注入为例，修改xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/util https://www.springframework.org/schema/util/spring-util.xsd">

    <import resource="dependency-lookup.xml"/>
    <bean id="userRepository"
          class="org.snippets.spring.ioc.overview.dependency.repo.UserRepository"
          autowire="byType"> <!-- 自动注入-->
    </bean>
</beans>
```

增加autowire="byType" 即可


## 完整代码

[Github](https://github.com/GreyZeng/thinking-in-spring/java-beans-demo)

## 参考资料

[小马哥讲Spring核心编程思想](https://time.geekbang.org/course/intro/100042601)