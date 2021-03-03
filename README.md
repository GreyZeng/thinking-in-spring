Spring学习笔记

[JavaSE实现IoC](docs/JavaSE实现IoC.md)

[Spring的依赖查找和依赖注入](docs/Spring的依赖查找和依赖注入.md)

Bean初始化

- BeanDefinitionBuilder
- new GenericBeanDefinition

Bean的命名(非强制性)

- BeanNameGenerator
- AnnotationBeanNameGenerator

Bean的别名(一般XML配置)

- Alias

BeanDefinition的注册

- XML
- 注解 BeanDefinitionRegistryDemo
    - @Bean
    - @Component
    - @import
- Java API配置元信息