# Linux学习笔记

作者：[Grey](https://www.cnblogs.com/greyzeng/)

原文地址：

[Github](https://github.com/GreyZeng/articles/blob/master/Linux%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0.md)

[语雀](https://www.yuque.com/greyzeng/uzfhep/xb2ggs)

[博客园](https://www.cnblogs.com/greyzeng/articles/14093197.html)

## Linux的安装

说明：本安装说明是基于Windows10 下VMware安装Linux，

### 环境

VMware-workstation-full-15.5.2-15785246

[CentOS-6.10-x86_64-minimal.iso](https://mirrors.aliyun.com/centos/6/isos/x86_64/CentOS-6.10-x86_64-minimal.iso)

**其他版本的安装可作为参考**

### 安装步骤

打开VMware，

点击创建新的虚拟机，在新建虚拟机向导这里选择**自定义**,然后依次按照截图步骤进行安装

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588731194274-fa975902-3d75-4180-a2c3-c3c9e96bdd00.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588731210347-c56e3aeb-377a-45d9-98f0-1a65381cdc60.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588731226948-bb97a6ec-6d5e-4ab5-b349-4f7646476814.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588731236746-72920371-f1e4-45ec-be36-b30c3d62672e.png)

**这里选择对应的Linux版本（注意安装的是CentOS7还是CentOS6）**

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588731277060-431c2e05-f713-4007-ad1c-b96ba4ec87d8.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1607237193432-c6fb6068-55d7-465e-af52-309bde6ad0dd.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588731334282-81e3a5be-d121-4067-9f78-ef662f3802f4.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588731347943-5b25a6d0-e817-4d3d-b6b2-6613d3bcd8d3.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588597959841-2c155cdb-e104-48b1-907c-192a382a572d.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588597995260-4e8cb8c9-697f-43ef-84cd-dcad47c45c01.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588598004053-485f3195-2300-437c-a6c4-d3362add169d.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588598023110-5c7de5f2-3589-46f2-a85b-5f984d7959e6.png)

**磁盘容量大小视你本机磁盘大小而定，最好不要小于建议大小值**

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588731404238-844cb5a3-144b-4057-95fd-9e4c131550c4.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588598087587-1cd31895-bd2d-4136-982b-1f8d578a62d7.png)

默认启动即可，在启动过程中，有可能会出现如下错误：

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588731755864-7f1860d1-c7b0-44ef-8440-e0603046d391.png)

这里是[解决方案](http://www.bubuko.com/infodetail-3314116.html)

点击：编辑虚拟机设置

在CD/DVD这里，选择使用ISO映像文件，选择对应的CentOS的iso文件

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1607237437889-d77b77f1-9111-40f1-ac51-799b8cd75ed9.png)

点击：开启此虚拟机

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1607237330806-b62faae9-bdda-4773-9ec8-d731affd11e7.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1607238228125-b7cb5105-73ad-4450-b54e-7c6fc2415a23.png)

此页面两个选项，请**选择Skip**

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588598508386-1519ed81-ee62-4e1c-8782-e893bbbe8294.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588598553774-151ca285-a618-4426-8cf8-7c4f98d5e8a0.png)

**如果在开始设置内存的时候，低于1G，则不会出现图形界面**

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1607238404259-b3d4da30-81fd-4ad6-b328-c1125e495598.png)
![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1607238419994-750b706c-d5f8-4454-9102-3dc20f75d4e2.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588598615601-59497ad7-14b0-4b74-ab31-568433bee6f8.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588598642691-06686a41-5da1-4413-873c-fe579ecfd2bd.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588598725447-32468b2d-7a49-4841-a88c-6b36edf77860.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588598783188-f997ebbe-ce29-42ae-9edd-b12173f4520f.png)

设置Root的密码，不少于6位，否则校验不通过

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1607238599459-a58275e0-1e9b-40d5-b27c-62c92e125821.png)

**这一步选择：Create Custom Layout**

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588598863841-b2da782a-b6db-4f8a-b43f-d6ca70df2ccf.png)

接下来对硬盘进行分区分区

第一块是：sda

第二块是：sdb

第三块是：sdc

我们需要分成以下几个区：

1. boot 引导程序区
2. swap交换区 内存和磁盘，当内存不足的时候，会启动一个进程，将内存转移到交换区中

启动app 内存不足->写入交换区

3. 用户区

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588599207224-2d42adba-84f5-47c7-8d52-3e4e8bc11092.png)

选中sda，创建第一个分区

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588599222009-9e98acd9-2053-46a5-8fa1-2db2e9314d2c.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588599279810-3b534624-fedd-464e-a1a5-2314c5e85b7e.png)

创建第二个分区

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588599339722-18f389b3-8f04-43df-9a6b-b57f438796e7.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588599395160-5c0530b0-6de9-4577-a3cb-531a2115bfdb.png)

创建第三个分区（用户分区）, **勾选：Fill to maximum allowable size ： 表示剩余资源都挂到根目录下**

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588599559125-15eb65ba-55d8-4fbc-bbde-14fe8173a637.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588599596711-118afd9c-f1a9-4e80-a4db-7fa16d0109fc.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588599723441-2c0d890a-48ef-45c7-836a-afbd6dcef0d3.png)

Linux安装完成

## 网络配置

1. 找到网卡位置：

   ```bash
   cd /etc/sysconfig/network-scripts/
   ```

2. 配置网卡协议

   ```bash
   vi ifcfg-eth0
   ```

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588600576104-13b1a6a6-645d-4ead-9663-77f21b810c0c.png)

dhcp：自动获取，我们要改成static

VMware这里，点编辑->虚拟网络编辑器

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588600818440-616d00b1-64c2-4084-a2d7-d37a18dad968.png)

选择：NAT设置

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588600831779-f4b6a8c8-62f4-4dac-bd0d-7a0054892b41.png)

如果选中以下

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588600922770-abea5879-f8d7-4f53-abde-91d5a7ab6919.png)

那么在主机中会有：

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588600943876-e0eb111f-7620-45e8-91c0-f5ed2ed05f85.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588600974333-59d832fa-4839-426a-a63f-93ec4dd1e502.png)

可以看到，116.1和116.2都被占用了（你实际在操作的时候，可能是xxx.1,xxx.2,不一定是116）

所以：

116.0：网络号

116.255：广播地址

116.1：虚拟网卡地址

116.2：网关

可以配置的地址：3-254

所以可以做如下配置：

IPADDR=192.168.116.66

NETMASK=255.255.255.0

GATEWAY=192.168.116.2

DNS1=114.114.114.114

DNS2=192.168.116.2

**说明：我这里是116，你在操作的时候，不一定是116， 按照你本机显示的地址来配置即可。**

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588601798136-2462dde1-475d-4639-87d6-e2068fdb1393.png)

重启网卡服务

```bash
service network restart 
```

测试：

ping www.baidu.com 查看是否有数据接收到。

## 快照与克隆

克隆之前，先打快照

1. 关闭虚拟机

2. 在节点这里，选择快照->快照管理器

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588602217391-024b5957-a05c-44d8-88cf-6bf5b8d462ca.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588602249181-10bce7a3-1f2f-4a74-a7d9-81b7e193e87e.png)

为快照设置一个名字，假设为base，

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588602264236-9fbc5596-994e-49c7-ad32-06c3946f421f.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588602313478-4b6e322c-f5c8-4d0d-8cec-5531105cdd6b.png)

然后开始克隆，在节点上右键：克隆->选择现有快照，下拉框选base

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588602354675-a36dbbea-6760-48e1-bd0c-65a265ce9e41.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588602371094-d68314db-302a-4378-8348-2902e44cdbd1.png)

**这里选择创建链接克隆**可以节省资源：

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588602389990-2793f0df-565b-46f9-8dd7-2f1484640a46.png)

为克隆的虚拟机设置一个名称，假设叫：node02

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1607239887177-c367af40-1fe3-469c-ada4-e43776f688f5.png)

克隆后，启动node02

启动后，通过root登录进去（node01配置的root用户名和密码），首先修改node02网卡信息

执行：

```bash
cd /etc/sysconfig/network-scripts/

vi ifcfg-eth0
```

可以看到这个配置是copy了node01的配置，需要把这些配置修改一下，IPADDR设置为一个和node01不一样的地址即可（node01是66，node02设置为68即可）

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588602565000-71e96645-e197-4e58-a101-36d7b7880cc5.png)

接下来，需要修改node02的hostname

```bash
cd /etc/sysconfig/

vi network
```

把hostname改成node02即可

最后，需要在node02上删除一个文件(**因为这个文件中保存了网卡物理地址和网卡的名的关系，删除后，这个文件会自动生成**)，这个文件是

```bash
/etc/udev/rules.d/70-persistent-net.rules
```

通过rm命令删除这个文件：

```bash
rm -f /etc/udev/rules.d/70-persistent-net.rules
```

node02克隆完毕

然后重启node02的网络

```bash
service network restart
```

最后重启node02虚拟机 即可

node02克隆完毕



## 配置ssh密钥连接Linux

首先Win10系统上需要有OpenSSH，像这样：终端输入ssh

![](https://cdn.nlark.com/yuque/0/2020/png/757806/1583580986229-a39ca5f4-a6df-43c1-9e57-e85003836fa0.png#align=left&display=inline&height=136&margin=%5Bobject%20Object%5D&originHeight=163&originWidth=689&status=done&style=none&width=576)

这样就是有的（好像Win10 1809+默认就是有的）。
然后生成密钥对：
ssh-keygen -t rsa
接着按提示信息可根据个人需求选择，这里是默认（连续三个回车即可）。
生成的密钥对默认保存在当前用户的根目录下的.ssh目录中（C:\Users\username\.ssh）：

![](https://cdn.nlark.com/yuque/0/2020/png/757806/1583580986262-866d2d74-0511-4d07-b535-42f34c103bd8.png#align=left&display=inline&height=128&margin=%5Bobject%20Object%5D&originHeight=153&originWidth=691&status=done&style=none&width=576)

接着我们将公钥id_rsa.pub上传至Linux服务器（保存到你要连接的用户根目录下~/.ssh/中，没有.ssh目录则创建）：
修改/etc/ssh/sshd_config配制文件，修改以下内容
RSAAuthentication yes
PubkeyAuthentication yes
PasswordAuthentication no

                                     
上传好后，将Linux中的id_rsa.pub重命名为authorized_keys，更改文件权限为600，更改.ssh目录权限为700：
mv id_rsa.pub authorized_keys
chmod 600 authorized_keys
chmod 700 .ssh


然后就可以通过ssh方式连接到Linux

![](https://cdn.nlark.com/yuque/0/2020/png/757806/1583580986286-d1deb211-ea47-403c-9ffb-048f2a97373f.png#align=left&display=inline&height=507&margin=%5Bobject%20Object%5D&originHeight=507&originWidth=504&status=done&style=none&width=504)




## Linux的命令

分为内部命令和外部命令 内部命令（Shell自带的命令）

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588604727521-7fce2dd3-d1f9-4bc5-95ac-7071c77c78d7.png#align=left&display=inline&height=52&margin=%5Bobject%20Object%5D&name=image.png&originHeight=52&originWidth=275&size=3550&status=done&style=none&width=275)

外部命令（不是Shell自带的命令，由用户安装的）

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588604749548-b6dde106-d9d3-4529-95ff-f9891a6c0bca.png#align=left&display=inline&height=59&margin=%5Bobject%20Object%5D&name=image.png&originHeight=59&originWidth=300&size=3679&status=done&style=none&width=300)

查看命令是一个什么类型的文件

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588604859481-c0c12d62-f6b8-448c-a17f-97024f9785ae.png#align=left&display=inline&height=74&margin=%5Bobject%20Object%5D&name=image.png&originHeight=74&originWidth=773&size=9768&status=done&style=none&width=773)

查看ifconfig命令在哪个位置

```shell
whereis ifconfig
```

什么是Shell? bash shell，就是一个程序，就是Linux系统安装的一个软件

root/密码写对后，直接进入bash shell软件

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588605290609-77801687-5dca-4abf-8eaf-483e70d72e06.png#align=left&display=inline&height=294&margin=%5Bobject%20Object%5D&name=image.png&originHeight=294&originWidth=680&size=84812&status=done&style=none&width=680)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588605354987-f9976149-de96-4db0-8e20-28c521255c87.png#align=left&display=inline&height=78&margin=%5Bobject%20Object%5D&name=image.png&originHeight=78&originWidth=276&size=23804&status=done&style=none&width=276)
echo $PATH
![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588605392931-c4e9cc67-60a5-46ec-9490-5b2c1080ebab.png#align=left&display=inline&height=41&margin=%5Bobject%20Object%5D&name=image.png&originHeight=41&originWidth=514&size=3691&status=done&style=none&width=514)

内部命令的帮助用help指令，外部命令的帮助用man指令

echo $$: 当前bash shell的进程号

如果平时退出不了某个程序，可以复制一个ssh对话，用ps -ef找到那个进程，用kill -9 退出即可

bash shell在执行命令的时候，做了两步优化：

1. 通过PATH来
1. 通过hash来，hash查看，hash -r（清空hash）

## Shell


编写脚本时候要赋予该文件执行权限`chmod u+rx filename`


如果bash执行，不需要赋予执行权限


bash ./filename.sh
./filename.sh
以上两种执行方式都是新开一个进程


source ./filename.sh
.filename.sh
这种方式执行不会产生新的子进程


```shell
#!/bin/bash/
cd /tmp
pwd
```


输入重定向符号 <


read var < /path/to/a/file


输出重定向 >   >> 2> &>


echo 123 > /path/to/a/file  清空输入
echo 123 >> /path/to/a/file 追加
echo 12343 2> /path/to/a/file 错误输入
echo 122 &> /path/to/a/file 全部输入


```shell
#!/bin/bash

cat > /data/m.sh << EOF
echo "hello bash"
EOF
```


变量赋值


- a=123
- let a=10+2
- l=ls
- letc=$(ls -l /etc) 或 letc=`ls -l /etc`
- 变量值有空格等特殊字符可以包括在"" 或 ``中
- echo ${变量名} 查看变量的值
- ![](https://g.yuque.com/gr/latex?%7B%7D%E5%8F%AF%E4%BB%A5%E7%9C%81%E7%95%A5%E4%B8%BA#card=math&code=%7B%7D%E5%8F%AF%E4%BB%A5%E7%9C%81%E7%95%A5%E4%B8%BA)



变量的默认作用范围
默认自己的shell进程中


变量的导出，让子进程获得父进程的变量值


- export 变量名
  变量的删除
- unset 变量名



环境变量：每个Shell打开都可以获得的变量


- set和env命令
- $? 上一条命令是否正确执行（正确：0， 错误：1）
- ![](https://g.yuque.com/gr/latex?%20%E6%98%BE%E7%A4%BA%E5%BD%93%E5%89%8D%E8%BF%9B%E7%A8%8B%E7%9A%84pid%0A#card=math&code=%20%E6%98%BE%E7%A4%BA%E5%BD%93%E5%89%8D%E8%BF%9B%E7%A8%8B%E7%9A%84pid%0A)

- $PATH
- $PS1



位置变量


- $1 $2 ... $n



```shell
#!/bin/bash

# $1 $2 ...$9 ${10}


echo $1
# 默认打印第二个参数值，如果为空则显示_
echo ${2-_}
```


环境变量配置文件所在目录


- /etc/profile
- /etc/profile.d/
- ~/.bash_profile
- ~/.bashrc
- /etc/bashrc



> 说明：/etc/下的配置文件，表示所有用户通用的配置，用户家目录的配置文件只能特定用户使用，
su - 用户名  login shell 所有都可以执行
su 用户名 nologin shell  /bashrc, /etc/bashrc



所用终端都应用新的环境变量: export PATH=$PATH:/new/to/path
让环境变量立即生效：source /etc/profile


定义数组


```shell
# 数组定义
IPTS=(10.0 1.0 3.0)

# 显示数组中所有元素
echo ${IPTS[@]}

#显示数组元素个数
echo ${#IPTS[@]}

显示数组的第一个元素
echo ${IPTS[0]}
```


Q：


- 内建命令不需要创建子进程
- 内建命令对当前shell有效


## Linux文件系统

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588638734029-d7f3e8d9-e5b6-42ab-ad0f-bfa9d99e4541.png#align=left&display=inline&height=775&margin=%5Bobject%20Object%5D&name=image.png&originHeight=775&originWidth=807&size=217381&status=done&style=none&width=807)
除了/boot的数据，其他目录下的数据都存在了sda3里面了

/var 可变化的文件，比如：日志文件，数据文件

更多文件类型说明见：
![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588639527449-93861280-c798-4615-ad30-2a992ffa29a1.png#align=left&display=inline&height=604&margin=%5Bobject%20Object%5D&name=image.png&originHeight=604&originWidth=687&size=223193&status=done&style=none&width=687)

```shell
## 级联创建目录
mkdir -p a/adir/bdir

mkdir a/adir a/bdir a/cdir
mkdir a/{1,2,3}dir


## 复制文件夹
cp -r a cpp/ ## 将a文件夹复制到cpp文件夹中，复制文件夹用 

```

stat和touch 组合使用，可以增量监控数据改变的时间 [linux命令系列 stat & touch](https://www.cnblogs.com/z-joshua/p/10042681.html)

## 命令积累

与时间服务器上的时间同步

1. 安装ntpdate工具

```shell
yum -y install ntp ntpdate
```

2. 设置时间为阿里服务器的时间

```shell
ntpdate ntp1.aliyun.com
```

3. 将系统时间写入硬件时间

```shell
hwclock –systohc
```

一屏可以显示的文件，可以用cat 一屏显示不出来的内容，用more命令，space翻页，无法回看 使用less 命令就可以往后推（space），按b键往前翻（less是将文件一次性load内存，所以文件大的时候只能用more）

head -n 文件名 前n行的数据 tail -n 文件名 后n行数据 tail -f 文件名 监控文件内容改变

管道命令 cat xxx | head -3 cat xxx作为输出流的形式作为后面命令的输入流

ls -l 无法接受前面的输出流的内容，如果要解决，需要这样用： echo "/" | xargs ls -l

head -5 xxx | tail -1 获取第五行的数据

```shell
screen -S yourname ## 新建一个叫yourname的session
screen -ls         ## 列出当前所有的session
screen -r yourname ## 回到yourname这个session
screen -d yourname ## 远程detach某个session
screen -d -r yourname ## 结束当前session并回到yourname这个session
``` 


## 参考资料

[Linux命令行大全](https://book.douban.com/subject/22226727/)

[Linux就该这么学](https://book.douban.com/subject/27198046/)

[Linux预习资料](https://pan.baidu.com/s/1qLY7x29EtZO-uz3a06QzFQ)  [提取码：7w30]

[Linux内核设计与实现](https://book.douban.com/subject/6097773/)

[极客时间-Linux实战技能100讲](https://time.geekbang.org/course/intro/100029601)