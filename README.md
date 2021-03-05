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

Bean实例化

- 常规方法
- 非常规方法

Bean的初始化

顺序： @PostConstruct  > InitializingBean#afterPropertiesSet 方法 > initMethod

Bean的延迟初始化

延迟初始化和非延迟对象的差异： 应用上下文启动前后 BeanInitialDemo

Bean的销毁 @PreDestroy > DisposableBean#destroy > destroyMethod

如何垃圾回收 Spring Bean

1. 关闭Spring 容器（应用上下文）
2. 执行GC
3. Spring Bean覆盖的finalize()方法被回调

