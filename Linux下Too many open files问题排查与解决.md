
作者：

[Grey](https://www.cnblogs.com/greyzeng/)

原文地址：

[Github](https://github.com/GreyZeng/articles/blob/master/Linux%E4%B8%8BToo%20many%20open%20files%E9%97%AE%E9%A2%98%E6%8E%92%E6%9F%A5%E4%B8%8E%E8%A7%A3%E5%86%B3.md)

[语雀](https://www.yuque.com/greyzeng/uzfhep/onlvr6)

[博客园](https://www.cnblogs.com/greyzeng/p/14297258.html)


> > Too many open files是Linux系统中常见的错误，从字面意思上看就是说程序打开的文件数过多，不过这里的files不单是文件的意思，也包括打开的通讯链接(比如socket)，正在监听的端口等等，所以有时候也可以叫做句柄(handle)，这个错误通常也可以叫做句柄数超出系统限制。引起的原因就是进程在某个时刻打开了超过系统限制的文件数量以及通讯链接数。

通过命令ulimit -a可以查看当前系统设置的最大句柄数是多少

```sh
core file size          (blocks, -c) 0
data seg size           (kbytes, -d) unlimited
scheduling priority             (-e) 0
file size               (blocks, -f) unlimited
pending signals                 (-i) 31767
max locked memory       (kbytes, -l) 64
max memory size         (kbytes, -m) unlimited
open files                      (-n) 1024
pipe size            (512 bytes, -p) 8
POSIX message queues     (bytes, -q) 819200
real-time priority              (-r) 0
stack size              (kbytes, -s) 8192
cpu time               (seconds, -t) unlimited
max user processes              (-u) 31767
virtual memory          (kbytes, -v) unlimited
file locks                      (-x) unlimited
```
可以看到，open files的配置是1024，可以通过如下命令将open files增加

```sh
ulimit -n 65535
```

这种修改方式可以**临时**把文件打开数量增加到65535，但是系统重启后这个配置会失效。

还有一种方式是修改系统的配置文件，以Ubuntu为例，配置文件默认在

```bash
/etc/security/limits.conf
```

在这个配置文件中增加

```bash
* soft nofile 65535
* hard nofile 65535
```



此外，如果用supervisor托管和启动项目，会遇到这个配置无法生效的问题，原因在于supervisor会默认配置打开的句柄数量是1024，

如果要查看某个进程最大open files，可以通过这个进程的进程号对应的limits查看

```bash
cat /proc/进程号/limits
```

其中有一行是：

```bash
Max open files	1024	1024	bytes
```

supervisor托管的程序这一行默认都是supervisor配置的最大数量1024，这时需要手动改一下supervisor的配置文件，修改方式如下，以Ubuntu系统为例，找到supervisor的配置文件supervisord.conf

在[supervisord]选项中，增加minfds选项的配置

```bash
[supervisord]
minfds=65535                  ; min. avail startup file descriptors; default 1024
```

配置完毕后，需要重启supervisor(以systemctl为例)

```bash
systemctl restart supervisor
```

即可生效

在通过：

```bash
cat /proc/进程号/limits
```

查看下对应进程的可open files的数量

```bash
Max open files	65535	65535	bytes
```



