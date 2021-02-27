作者：[Grey](https://www.cnblogs.com/greyzeng/)

原文地址：

[语雀](https://www.yuque.com/greyzeng/uzfhep/fa3zzc)

[博客园](https://www.cnblogs.com/greyzeng/p/14456314.html)

Java SE 提供了三种方式，可以实现IoC，分别为：

- Java Beans
- Java ServiceLoader SPI
- JNDI（Java Naming and Directory Interface）


## Java Beans 方式

java.beans包下的 Introspector 类提供了一个 getBeanInfo的方法，可以获取一个类的信息

```java
BeanInfo bi=Introspector.getBeanInfo(User.class,Object.class);
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

由于我们注入属性值的时候，我们注入的东西永远是一个字符串类型，如果需要注入的属性是其他类型（非字符串）， 比如User类中，有一个属性是address，这个address是一个对象类型，

我们应该如何定义一个转换器，将字符串类型的值转换为我们需要的对象类型呢？

我们需要通过设置一个AddressEditor来实现这个转换，这个AddressEditor有两种实现方式， 一是实现PropertyEditor接口，另外一种方式是继承PropertyEditorSupport类，
由于我们只需要实现一些简单的转换，PropertyEditorSupport提供了更为便利的实现方式，所以我们采用继承PropertyEditorSupport类的方法，来实现类型的转换，

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

例如： “贝克街|221” 这个字符串 会将“贝克街”赋给name，221赋给num

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

但是我们需要重写setAsText方法，即：将字符串类型按照我们定义的规则转换成对应需要的类型即可，同理，我们可以实现一个DateEditor，让“yyyy-MM-dd”这样类型的字符串转换成日期格式。

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
PropertyEditor editor = findEditor(propertyType);
if (editor != null) {
    editor.setAsText(parameters.get(pd.getName()));
    try {
        writeMethod.invoke(user, editor.getValue());
    } catch (IllegalAccessException | InvocationTargetException e) {
        e.printStackTrace();
    }
} else {
    System.out.println("no editor for:" + pd.getName());
}
```

主函数调用示例

```java
public static void main(String[] args) throws Exception {
    Map<String, String> parameters = new HashMap<String, String>() {
        {
            //这里的key要和Node里面的属性名一致
            put("name", "福尔摩斯");
            put("address", "贝克街|221");
            put("birthday", "1854-01-06");
        }
    };
    User convert = PropertyEditorSample.convert(parameters);
    System.out.println(convert);
}
```

运行结果

```json
User{name='福尔摩斯', birthday=Thu Jan 05 23:54:17 CST 1854, address=Address{name='贝克街, 221 号}}
```

## SPI方式

定义支付接口PayService

```java
public interface PayService {
    void pay();
}
```

定义多个实现：

```java
public class WeixinpayService implements PayService{
    @Override
    public void pay() {
        System.out.println("微信支付");
    }
}
```

```java
public class AlipayService implements PayService{
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
ServiceLoader<PayService> serviceLoader = ServiceLoader.load(PayService.class);

for (PayService ele : serviceLoader) {
    ele.pay();
}
```

其中ServiceLoader.load方法可以把所有配置的PayService实现得到

执行结果：

```java
支付宝支付
微信支付
```


## JNDI方式

## 完整代码

[Github](https://github.com/GreyZeng/thinking-in-spring/java-beans-demo)

## 参考资料

[小马哥讲Spring核心编程思想](https://time.geekbang.org/course/intro/100042601)

[PropertyEditorSupport的使用](https://blog.csdn.net/u010398771/article/details/82968574)

[Java SPI机制 - ServiceLoader](https://zhuanlan.zhihu.com/p/67665359)