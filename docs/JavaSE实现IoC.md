作者：[Grey](https://www.cnblogs.com/greyzeng/)

原文地址：[https://www.cnblogs.com/greyzeng/p/14456876.html](https://www.cnblogs.com/greyzeng/p/14456876.html)

## 什么是IoC

简单地说，IoC 是反转控制，类似于好莱坞原则，主要有依赖查找和依赖注入实现

> 依赖查找是主动或手动的依赖查找方式，通常需要依赖容器或标准API实现。 而依赖注入则是手动或自动依赖绑定的方式，无需依赖特定的容器和API

## JavaSE中的IoC实现方式

Java SE 提供了三种方式，可以实现IoC，分别为：

- Java Beans
- Java ServiceLoader SPI
- JNDI（Java Naming and Directory Interface）

### Java Beans

java.beans包下的 Introspector 类提供了一个 getBeanInfo的方法，可以获取一个类的信息

```java
BeanInfo bi = Introspector.getBeanInfo(User.class,Object.class);
```

如上，则可以获取User类对象的BeanInfo, 然后我们通过BeanInfo中的 getPropertyDescriptors 方法，可以获取到User对象中的所有属性和方法，

**注意：java beans中，对于set(xxx)方法，统一叫：writeMethod(), 对于get() 方法，统一叫：readMethod()**

```java
Stream.of(bi.getPropertyDescriptors()).forEach(pd->{
        Class<?> propertyType=pd.getPropertyType();
        Method writeMethod=pd.getWriteMethod();
        });
```

获取到方法和属性名称后，通过反射即可把对应的值设置到对应的属性中

```java
writeMethod.invoke(name,value);
```

由于我们注入属性值的时候，我们注入的东西永远是一个字符串类型，如果需要注入的属性是其他类型（非字符串）， 比如User类中，有一个属性是address，这个address是一个对象类型，我们应该如何定义一个转换器，将字符串类型的值转换为我们需要的对象类型呢？

我们需要通过设置一个AddressEditor来实现这个转换，这个AddressEditor有如下两种实现方式： 

- 实现PropertyEditor接口

- 继承PropertyEditorSupport类，重写setAsText方法

PropertyEditorSupport类提供了一些比较便利的实现方式，所以我们采用继承PropertyEditorSupport类的方法，来实现类型的转换，

Address类的设计是：

```java
public class Address {
    private String name;
    private Integer num;
    // 省略 get / set / toString
}
```

我们的定义的规则如下,

输入的字符串用|来分割 name 和 num属性

例如： “贝克街|221” 这个字符串 会将“贝克街”赋给name，221赋给num，所以，我们重写setAsText方法的逻辑如下：

```java
public class AddressEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] tokens = text.split("\\|");
        Address address = new Address();
        address.setName(tokens[0]);
        address.setNum(Integer.valueOf(tokens[1]));
        setValue(address);
    }
}
```



同理，我们可以实现一个DateEditor，让“yyyy-MM-dd”这样类型的字符串转换成日期格式。

```java
public class DateEditor extends PropertyEditorSupport {

    static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        LocalDate localDate = LocalDate.parse(text, dtf);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        setValue(Date.from(instant));
    }
}
```

然后，我们需要使用java beans中的PropertyEditorManager类的registerEditor方法把这两个Editor注册进来

```java
registerEditor(Address.class,AddressEditor.class);
registerEditor(Date.class,DateEditor.class);
```

最后，PropertyEditorManager的findEditor方法就可以根据我们前面得到的属性类型，找到对应的Editor来对值进行转换，转换成我们需要的属性类型的值

```java
PropertyEditor editor=findEditor(propertyType);
if(editor!=null){
    // 这一步就是为所有属性找到其对应的解析器
    editor.setAsText(parameters.get(pd.getName()));
    try{
        writeMethod.invoke(user,editor.getValue());
    }catch(IllegalAccessException|InvocationTargetException e){
        e.printStackTrace();
    }
}else{
    System.out.println("no editor for:"+pd.getName());
}
```

主函数调用示例

```java
public static void main(String[]args)throws Exception{
    Map<String, String> parameters=new HashMap<String, String>(){
        {
            //这里的key要和Node里面的属性名一致
            put("name","福尔摩斯");
            put("address","贝克街|221");
            put("birthday","1854-01-06");
        }
    };
    User convert=PropertyEditorSample.convert(parameters);
    System.out.println(convert);
}
```

运行结果

```
User{name='福尔摩斯', birthday=Thu Jan 05 23:54:17 CST 1854, address=Address{name='贝克街, 221 号}}
```

### SPI

定义支付接口PayService

```java
public interface PayService {
    void pay();
}
```

定义多个实现：

```java
public class WeixinpayService implements PayService {
    @Override
    public void pay() {
        System.out.println("微信支付");
    }
}
```

```java
public class AlipayService implements PayService {
    @Override
    public void pay() {
        System.out.println("支付宝支付");
    }
}
```

在resources目录下建立META-INF文件夹，在META-INF文件夹下建立services目录，同时建立一个文件，名称为接口的全路径名，以这个项目为例, PayService的全路径名称为：

```java
org.snippets.ioc.java.spi.PayService
```

在这个文件内，把实现类的全路径名写进去：

```java
org.snippets.ioc.java.spi.AlipayService
org.snippets.ioc.java.spi.WeixinpayService
```

客户端调用：

```java
ServiceLoader<PayService> serviceLoader=ServiceLoader.load(PayService.class);

for(PayService ele:serviceLoader){
    ele.pay();
}
```

其中ServiceLoader.load方法可以把所有配置的PayService实现得到

执行结果：

```java
支付宝支付
微信支付
```

### JNDI方式

定义一个Person类

```java
public class Person implements Remote, Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String password;

    // 省略set / get方法
}
```

实现JNDI的客户端，实现初始化Person和查找Person两个功能

```java
public static void initPerson()throws Exception{
    //配置JNDI工厂和JNDI的url和端口。如果没有配置这些信息，将会出现NoInitialContextException异常
    LocateRegistry.createRegistry(3000);
    System.setProperty(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
    System.setProperty(Context.PROVIDER_URL,"rmi://localhost:3000");


    InitialContext ctx=new InitialContext();

    //实例化person对象
    Person p=new Person();
    p.setName("zc");
    p.setPassword("123");

    //将person对象绑定到JNDI服务中，JNDI的名字叫做：person。
    ctx.bind("person",p);
    ctx.close();
}

public static void findPerson()throws Exception{
    //因为前面已经将JNDI工厂和JNDI的url和端口已经添加到System对象中，这里就不用在绑定了
    InitialContext ctx=new InitialContext();

    //通过lookup查找person对象
    Person person=(Person)ctx.lookup("person");

    //打印出这个对象
    System.out.println(person.toString());
    ctx.close();
}
```

## 完整代码

[Github](https://github.com/GreyZeng/thinking-in-spring)

## 参考资料

[小马哥讲Spring核心编程思想](https://time.geekbang.org/course/intro/100042601)

[PropertyEditorSupport的使用](https://blog.csdn.net/u010398771/article/details/82968574)

[Java SPI机制 - ServiceLoader](https://zhuanlan.zhihu.com/p/67665359)

[02_Java通信_JNDI_demo1](https://blog.csdn.net/chaojixiaozhu/article/details/84617709)