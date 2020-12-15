# Java各版本新增特性, Since Java 9

作者：[Grey](https://www.cnblogs.com/greyzeng/)


原文地址：

[Github]()

[语雀]()

[博客园]()


## Java 9


[jshell](https://docs.oracle.com/en/java/javase/13/jshell/introduction-jshell.html)


无法用单个下划线作为变量名称


```
int _ = 3; // java9 or above , error
```


```
 String a = Objects.requireNonNullElse(m,"Bc"); // 若m不为null，则a = m，若m为null，则a = "Bc"
```


```
-cp, -classpath, --class-path(Java9新增)
```


Multi-Release JAR Files


```
--release
--class-path instead of -classpath
--version instead of -version
--module-path option has a shortcut -p
```


更多，见[jeps](http://openjdk.java.net/jeps/293)


Java8中，接口可以有静态方法的默认实现，例：


```java
public interface Test {
    public static void print() {
        System.out.println("interface print");
    }

    default void pout() {
        System.out.println();
    }
}
```


Java9中，可以支持private的静态方法实现。


```java
public interface Test {
    private static void print() {
        System.out.println("interface print");
    }

    static void pout() {
        print();
    }
}
```


```java
Optional.ofNullable(date).orElseGet(() -> newDate()); // date为null，才会执行newDate()方法，否则不执行newDate()方法
Optional.ofNullable(date).orElse(newDate()); // 无论date是否为null，都会执行newDate()方法
```


Java7中，可以使用try-with-Resources


```java
try(Resouce res = ...) {
    work with res
}
```


res.close()会被自动执行


例：


```java
try (var in = new Scanner(new FileInputStream("C:\\Users\\Young\\Desktop\\新建文件夹\\1.tx.txt"), StandardCharsets.UTF_8);
             var out = new PrintWriter("C:\\Users\\Young\\Desktop\\新建文件夹\\out.txt", StandardCharsets.UTF_8)) {
            while (in.hasNext()) {
                out.println(in.next().toUpperCase());
            }
        }
```


in 和 out执行完毕后都会自动关闭资源


在Java9 中，你可以在try中预先声明资源
例：


```java
  public static void printAll(String[] lines, PrintWriter out) {
       try (out) { // effectively final variable
           for (String line : lines) {
               out.println(line);
           } // out.close() called here
       }
   }
```


StackWalker用法示例


```java
public class App {
    /**
     * Computes the factorial of a number
     *
     * @param n a non-negative integer
     * @return n! = 1 * 2 * . . . * n
     */
    public static int factorial(int n) {
        System.out.println("factorial(" + n + "):");
        var walker = StackWalker.getInstance();
        walker.forEach(System.out::println);
        int r;
        if (n <= 1) {
            r = 1;
        } else {
            r = n * factorial(n - 1);
        }
        System.out.println("return " + r);
        return r;
    }

    public static void main(String[] args) {
        try (var in = new Scanner(System.in)) {
            System.out.print("Enter n: ");
            int n = in.nextInt();
            factorial(n);
        }
    }
}
```


Java 9 expands the use of the diamond syntax to situations where it was previously not accepted. For example , you can now use diamonds with anonymous subclasses.


```java
ArrayList<String> list = new ArrayList<>(){
            @Override
            public String get(int index) {
                return super.get(index).replaceAll(".","*");
            }
        };
```


## Java 10


无需定义变量类型，通过var关键字+初始化的值，可以推测出变量类型


```java
var a = 2; // a表示int
var b = "hello"; // b 表示String
var date = new java.util.Date();
var obj = new Custome(); // 自定义对象
```


## Java 11


```java
String repeated = "Java".repeat(3); // 三个Java字符串连接
```


JDK提供了[jdeprscan](https://docs.oracle.com/en/java/javase/11/tools/jdeprscan.html) 来检查你的代码是否使用了deprecated的方法


## 专题


### Lambda Expression
| Method Reference | Equivalent Lambda Expression | Notes |
| --- | --- | --- |
| separator::equals | x -> separator.equals(x) | This is a method expression with an object and an instance method. The lambda parameter is passed as the explicit parameter of the method |
| String::trim | x -> x.trim() | This is a method expression with a class and an instance method. The lambda parameter becomes the explicit parameter of the method |
| String::concat | (x, y) -> x.concat(y) | Again, we have an instance method, but this time, with an explicit parameter. As before, the first lambda parameter becomes the implicit parameter, and the remaining ones are passed to the method |
| Integer::valueOf | x -> Integer::valueOf(x) | This is a method expression with a static method. The lambda parameter is passed to the static method |
| Integer::sum | (x, y) -> Integer::sum(x, y) | This is another static method, but this time with two parameters. Both lambda parameters are passed to the static method. The Integer.sum method was specifically created to be used as a method reference. As a lmbda, you could just write (x, y)->x + y |
| Integer::new | x -> new Integer(x) | This is a constructor reference. The lambda parameters are passed to the constructor |
| Integer[]::new | n -> new Integer[n] | This is an array constructor reference. The lambda paramter is the array length |

| Functional Interface | Parameter Types | Return Types | Abstract Method Name | Description | Other Method |
| --- | --- | --- | --- | --- | --- |
| Runnable | none | void | run | Runs an action without arguments or return value |  |
| Supplier<T> | none | T | get | Supplies a value of type T |  |
| Consumer<T> | T | void | accept | Consumes a value of type T | andThen |
| BiConsumer<T,U> | T,U | void | accept | Consumes  value of types T and U | andThen |
| Function<T,R> | T | R | apply | A function with argument of type T | compose, andThen, identity |
| BiFunction<T,U,R> | T,U | R | apply | A function with arguments of types T and U | andThen |
| UnaryOperator<T> | T | T | apply | A unary operator on the type T | compose, andThen, identity |
| BinaryOperator<T> | T,T | T | apply | A binary operator on the type T | andThen, maxBy, minBy |
| Predicate<T> | T | boolean | test | A boolean-valued function | and, or, negate, isEqual |
| BiPredicate<T,U> | T,U | boolean | test | A boolean-valued function with two argumnets | and,or,negate |



Functional interfaces for Primitive Types


**p, q is int ,long double; P, Q is Int, Long, Double**

| Functional Interface | Parameter Types | Return Types | Abstract Method Name |
| --- | --- | --- | --- |
| BooleanSupplier | none | boolean | getAsBoolean |
| PSupplier | none | p | getAsP |
| PConsumer | p | void | accept |
| ObjPConsumer<T> | T,p | void | accept |
| PFunction<T> | p | T | apply |
| PToQFunction | p | q | applyAsQ |
| ToPFunction<T> | T | p | applyAsP |
| ToPBiFunction<T,U> | T,U | p | applyAsP |
| PUnaryOperator | p | p | applyAsP |
| PBinaryOperator | p,p | p | applyAsP |
| PPredicate | p | boolean | test |



## Service Loaders


## Proxies


## Logging


## Generic Programming


- E for the element type of a collection
- K and V for key and value type of a table
- T(and the neighboring letters U and S, if neccessary) for "any type at all"



```java
Pair<String> a = new Pair<>("A", "B");
Pair<Double> b = new Pair<>(1.1, 1.11);
System.out.println(a.getClass() == b.getClass()); // TRUE
```


in Java8


```java
public static <T> Pair<T> makePair(Supplier<T> constr) {
    return new Pair<>(constr.get(), constr.get());
}

Pair<String> p = Pair.makePair(String:new);
```


In general, there is no relationship between Pair<S> and Pair<T>, no matter how S and T are related.


BUT


```java
var managerBuiddies = new Pair<Manager>(ceo, cfo);
Pair<? extends Employee> buddies = managerBuddies;
```


## Collections


## Concurrency


## Stream


Java 8


```java
// 流操作
List<Integer> list = new ArrayList<>();
list.add(1);
list.add(2);
list.parallelStream().filter(i -> i > 1).count();
list.stream().filter(i -> i > 1).count();
Stream<String> words = Stream.of(contents.split(","));
// 创建流
var limits = new BigInteger("1000");
Stream<BigInteger> integerStream = Stream.iterate(BigInteger.ZERO, n -> n.compareTo(limits) < 0, n -> n.add(BigInteger.ONE));
System.out.println(integerStream.count());
```


如果我们持有的iterable对象不是集合，那么可以通过下面的调用将其转换成一个流


```java
StreamSupport.stream(iterable.spliterator(),false);
```


如果我们持有的是Iterator对象，并且希望得到一个由它的结果构成的流，那么可以使用下面的语句


```java
StreamSupport.stream(Spliterators.spliteratorUnknowSize(iterator, Spliterator.ORDERED),false);
```


**至关重要的是，在执行流的操作时，我们并没有修改流背后的集合。记住，流并没有收集其数据，数据一直存储在单独的集合中**


Optional


```java
String result = optionalString.orElse(""); // The wrapped string , or "" if none
String result = optionalString.orElseGet(() -> System.getProperty("myapp.default"));
String result = optionalString.orElseThrow(IllegalStateException::new);
```


消费Optinal值


```java
optionalValue.ifPresent(v -> result.add(v));
optionalValue.ifPresentOrElse(v -> System.out.println("Found" + v),
()-> logger.warning("no match"));
```


管道化Optional


```java
Optional<String> transformed = optionalString.filter(s -> s.length() >= 8).map(String::toUpperCase);
```


in Java9


```java
// 如果optionalString的值存在，那么result为optionalString，如果值不存在，那么就会计算lambda表达式，并使用计算出来的结果
Optional<String> transformed = optionalString.or(() -> alternatives.stream().findFirst());
```




## 参考资料

[Java核心技术·卷 I（原书第11版）](https://book.douban.com/subject/34898994/)

[Java核心技术·卷 II（原书第11版）](https://book.douban.com/subject/34935138/)
