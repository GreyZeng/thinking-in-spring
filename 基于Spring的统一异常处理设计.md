# 基于Spring的统一异常处理设计

作者： [Grey](https://www.cnblogs.com/greyzeng)

原文地址：

[Github](https://github.com/GreyZeng/articles/blob/master/%E5%9F%BA%E4%BA%8ESpring%E7%9A%84%E7%BB%9F%E4%B8%80%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86%E8%AE%BE%E8%AE%A1.md)

[博客园](https://www.cnblogs.com/greyzeng/p/11733327.html)

[语雀](https://www.yuque.com/greyzeng/uzfhep/kvfqv2)


Spring Boot中，支持RestControllerAdvice统一处理异常，在一个请求响应周期当中，如果Controller，Service，Repository出现任何异常，都会被RestControllerAdvice机制所捕获，进行统一处理。

**进行统一异常处理的目的也就是为了将千奇百怪的异常信息转换成用户可识别的错误信息**

### 统一异常拦截器

```Java
@RestControllerAdvice
@Slf4j
public class GlobalExceptionTranslator {

}
```

### 系统中的两类异常处理


第一类：业务自定义的异常，遇到这种异常，拦截器记录后，将业务异常自己的信息抛出。

```Java
@ExceptionHandler(BusinessException.class)
public JSONObject handleError(BusinessException e) {
    log.error("Business Exception {}", getStackTraceAsString(e));
    return error(e);
}
```

第二类：未定义异常，拦截器负责统一屏蔽原来的异常信息，转为服务器内部异常抛出。

```Java
@ExceptionHandler(Throwable.class)
public JSONObject handleError(Throwable undefined) {
    log.error("Internal Server Error {}", getStackTraceAsString(undefined));
    return error(new BusinessException(FAILURE));
}
```

调用者收到error的结果后，直接显示msg内容为用户可见的错误信息即可。

### 如何自定义一个业务异常

在业务开发中，通常无需进行Try catch处理，有业务异常直接抛出即可。如果需要定义一类通用的异常，则需要在自己业务模块下新建异常类，继承于 BusinessException

```Java

public class PaymentException extends BusinessException {

    //重写构造函数，从而定义该自定义异常的用户可见的错误信息
    public PaymentException() {
        super("支付失败");
        
    }
}
```

### 如何自定义一个框架级异常

在系统框架层面，已经预定义了一些常见的异常类，如：

类名|定义|预置错误信息
:-: | :-: | :-:
PermissionDenyException	|用户访问未授权的内容	|权限不足
ServiceNotFoundException|	调用微服务失败|	调用相关服务失败
其他异常| ...|...

在定义框架级异常时，除了需要编写异常类之外，如需要前端根据error CODE做对应的处理，就可以在ResultCode中增加定义。示例如下：

```Java
@Getter
@AllArgsConstructor
public enum ResultCode {

    /**
     * 操作成功
     */
    SUCCESS(HTTP_OK, "操作成功"),
    /**
     * 因程序内部错误操作失败（如不指定，则默认这个异常）
     */
    FAILURE(HTTP_INTERNAL_ERROR, "系统运行异常，请联系管理员"),
    /**
     * 用户访问未授权的内容
     */
    UN_AUTHORIZED(HTTP_UNAUTHORIZED, "权限不足"),

    /**
     * 调用微服务失败
     */
    NOT_FOUND(HTTP_NOT_FOUND, "调用相关服务失败");

    final int code;

    final String msg;
}
```

一个框架级异常的实现类
```Java

public class PermissionDenyException extends BusinessException {
    public PermissionDenyException() {
        super(UN_AUTHORIZED);
    }
}
```


如需要框架对该异常定义统一的策略，则需要在GlobalExceptionTranslator实现该策略，示例如下:

```Java
public class GlobalExceptionTranslator {   
    @ExceptionHandler(NewGlobalException.class)
    public JSONObject handleError(NewGlobalException e) {
        // 这里可以实现自定义的异常策略
        return error(new BusinessException(e.getResultCode(),e.getMessage()));
    }
}
```