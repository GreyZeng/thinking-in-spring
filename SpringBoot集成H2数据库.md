# Spring Boot集成H2数据库 

作者：[Grey](https://www.cnblogs.com/greyzeng/)

原文地址：

[Github](https://github.com/GreyZeng/articles/blob/master/SpringBoot%E9%9B%86%E6%88%90H2%E6%95%B0%E6%8D%AE%E5%BA%93.md)

[语雀](https://www.yuque.com/greyzeng/uzfhep/ny150b)

[博客园](https://www.cnblogs.com/greyzeng/p/12923389.html)


## 需求

平时学习的时候，涉及到一些连接数据库相关的操作，经常需要初始化本地数据库，比如装个MySQL，初始化一些脚本，比较麻烦，H2是内存数据库，Spring Boot可以在应用启动的时候对H2数据库初始化一些SQL脚本，这样的话，在学习/测试阶段，可以先使用H2数据库进行测试和学习，不需要额外安装MySQL数据库了。



**注意**

因为SQL脚本是可以在Spring Boot启动的时候初始化的，所以，假如你的SQL脚本中有一些删表删数据的操作，所以这种操作方式**不适合应用在生产数据库。**


## 环境

- JDK 1.8
- Maven 3.6.1
- Spring Boot 2.3.0.RELEASE

## 依赖

pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.0.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>org.hui</groupId>
    <artifactId>spring-boot-h2</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>spring-boot-h2</name>
    <description>Spring Boot for H2</description>
    <properties>
        <java.version>1.8</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```


## 配置文件

application.properties

```properties
spring.datasource.url=jdbc:h2:mem:testdb2
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=


#需要初始化的SQL脚本，指定位置为resources/db目录下，如果不指定，默认就在resources目录下
spring.datasource.schema=classpath:db/schema.sql
spring.datasource.data=classpath:db/data.sql

# H2控制台启用
spring.h2.console.enabled=true

# H2访问的URL
spring.h2.console.path=/h2
```

## SQL脚本

> 由于配置文件中指定了sql文件的位置，所以以下两个SQL文件放在resources/db目录下，你也可以指定其他目录，见配置文件的注释信息。

schema.sql

```sql
DROP TABLE IF EXISTS USER_INFO;
 
CREATE TABLE USER_INFO (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);
```

data.sql

```sql
INSERT INTO 
	USER_INFO (user_name, email)
VALUES
  	('grey', 'abc@gmail.com'),
  	('jack', 'jack@email.com');
```

## 测试类

```java
package org.hui.springbooth2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author zenghui
 * @date 2020-5-20
 */
@SpringBootApplication
public class H2Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(H2Application.class, args);
    }

    private final JdbcTemplate template;

    public H2Application(JdbcTemplate template) {
        this.template = template;
    }
    @Override
    public void run(String... args)  {
        String sql="SELECT count(*)  FROM USER_INFO";
        int count = template.queryForObject(sql, Integer.class);
        System.out.println("user count is " + count);
    }
}

```

## 运行

运行H2Application.java这个主类，控制台打印如下信息：

```
user count is 2
```

## 代码

[Github](https://github.com/GreyZeng/spring-boot-h2.git)

[Gitee](https://gitee.com/greyzeng/spring-boot-h2.git)
