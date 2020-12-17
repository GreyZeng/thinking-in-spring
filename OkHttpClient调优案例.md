# OkHttpClient调优案例

作者：[Grey](https://www.cnblogs.com/greyzeng/)

原文地址：

[语雀](https://www.yuque.com/greyzeng/uzfhep/rgmccw)

[博客园]()

[Github]()

实际案例
系统运行一段时间后，线程数量飙升，CPU持续居高不下

排查工具
[https://fastthread.io/](https://fastthread.io/)

这个工具是在线的，可以将dump日志文件上传上去后直接生成分析报告，并且可以导出为PDF
以下是我导出的分析结果
值得关注的指标：

![](https://cdn.nlark.com/yuque/0/2020/png/757806/1586074512154-71a72637-fe75-43de-80ba-8bc19b2a840a.png#align=left&display=inline&height=161&originHeight=393&originWidth=1014&status=done&style=none&width=415)

pool和OkHttp ConnectionPool占了前两位，由于很多同事开发的时候，没有定义有意义的线程名称，所以pool这2031个线程不好排查，

我们就从OkHttp ConnectionPool这里的线程数入手，很显然，这里是Okhttp的使用问题，

所以，我们排查了一下系统中所有使用Okhttp的地方，发现所有OkhttpClient的用法都是这样用的：

![](https://cdn.nlark.com/yuque/0/2020/png/757806/1586074512297-e9338d39-a7aa-4e8d-8bda-b5d084f04583.png#align=left&display=inline&height=336&originHeight=540&originWidth=666&status=done&style=none&width=415)

因为我们有好多的服务，每个服务都可能会用OkhttpClient，所以图中的getHttpClient()方法遍布各地，这个方法有什么问题呢？

我们进到了OkttpClient的源码中，看到了这个构造方法，找到了原因：

![](https://cdn.nlark.com/yuque/0/2020/png/757806/1586074512425-7b701cbd-1438-458e-9947-117e70765021.png#align=left&display=inline&height=312&originHeight=523&originWidth=695&status=done&style=none&width=415)

这个构造方法中，每次拿一个OkhttpClient客户端，

就要new一个ConnectionPool，

类似与每次new一个Thread就要new 一个ThreadPool一样，

而ConnectionPool完全是可以复用的，所以不需要new ConnectionPool，复用即可，

所以我们把OkhttpClient的获取逻辑修改成了以下：
![](https://cdn.nlark.com/yuque/0/2020/png/757806/1586074512565-ebe181c3-acd3-401d-a796-27063a73db95.png#align=left&display=inline&height=249&originHeight=662&originWidth=1104&status=done&style=none&width=415)

即每个OkhttpClient复用一个ConnectionPool，
为了验证，我特意在IDEA里面写了两个小程序来实验一下：

![](https://cdn.nlark.com/yuque/0/2020/png/757806/1586074512751-9516da11-3896-4d6f-bf67-80b86aad8050.png#align=left&display=inline&height=242&originHeight=926&originWidth=1591&status=done&style=none&width=415)
![](https://cdn.nlark.com/yuque/0/2020/png/757806/1586074512916-ed5635af-a87b-4bc3-a962-27eaf7e44c9d.png#align=left&display=inline&height=286&originHeight=937&originWidth=1358&status=done&style=none&width=415)

源码：

[OkHttpClientNotSharePool](https://github.com/GreyZeng/juc/blob/master/src/main/java/juc/okhttp/OkHttpClientNotSharePool.java)

[OkHttpClientSharePool](https://github.com/GreyZeng/juc/blob/master/src/main/java/juc/okhttp/OkHttpClientSharePool.java)

