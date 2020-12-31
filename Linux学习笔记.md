
作者：[Grey](https://www.cnblogs.com/greyzeng/)


原文地址：


[Github](https://github.com/GreyZeng/articles/blob/master/Linux%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0.md)


[语雀](https://www.yuque.com/greyzeng/uzfhep/xb2ggs)


[博客园](https://www.cnblogs.com/greyzeng/articles/14093197.html)


## Linux的安装


说明：本安装说明是基于Windows10 下VMware安装Linux，Linux版本是CentOS 7

*自2020年12月01日起，CentOS社区不再为CentOS 6版本提供安全更新，详情请查看[CentOS官方公告](https://wiki.centos.org/About/Product)。*


### 环境


VMware-workstation-full-15.5.2-15785246


CentOS-7-x86_64-Minimal-1908.iso


**其他版本的安装可作为参考**


### 安装步骤


打开VMware，


点击创建新的虚拟机，进入新建虚拟机向导,然后依次按照截图步骤进行安装


![1.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608912705881-f8721088-b5f7-4807-9f64-b539e16ec8dc.png#align=left&display=inline&height=440&margin=%5Bobject%20Object%5D&name=1.png&originHeight=440&originWidth=513&size=30124&status=done&style=none&width=513)
![2.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608912706641-0f667be4-33d1-44fa-81d8-c8e2c2f4a0e4.png#align=left&display=inline&height=440&margin=%5Bobject%20Object%5D&name=2.png&originHeight=440&originWidth=513&size=14544&status=done&style=none&width=513)
![3.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608912707506-b675ddeb-c1af-463a-98e2-6daaa6b6f360.png#align=left&display=inline&height=440&margin=%5Bobject%20Object%5D&name=3.png&originHeight=440&originWidth=513&size=11853&status=done&style=none&width=513)
![4.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608912708292-54d9e6cb-2455-46f5-aef4-0f41e72e1fe2.png#align=left&display=inline&height=440&margin=%5Bobject%20Object%5D&name=4.png&originHeight=440&originWidth=513&size=11432&status=done&style=none&width=513)
![5.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608912709067-1066608f-00bf-4628-826c-558e1be17b11.png#align=left&display=inline&height=440&margin=%5Bobject%20Object%5D&name=5.png&originHeight=440&originWidth=513&size=15434&status=done&style=none&width=513)
![6.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608912709906-6f11a8b8-dae7-48fc-ac8d-8e5a10f5b82b.png#align=left&display=inline&height=440&margin=%5Bobject%20Object%5D&name=6.png&originHeight=440&originWidth=513&size=14968&status=done&style=none&width=513)






点击完成即可，在启动过程中，有可能会出现如下错误：


![error](https://cdn.nlark.com/yuque/0/2020/png/757806/1588731755864-7f1860d1-c7b0-44ef-8440-e0603046d391.png#align=left&display=inline&height=171&margin=%5Bobject%20Object%5D&originHeight=171&originWidth=386&status=done&style=none&width=386)


这里是[解决方案](http://www.bubuko.com/infodetail-3314116.html)


点击：编辑虚拟机设置


在CD/DVD这里，选择使用ISO映像文件，选择对应的CentOS的iso文件

![7.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608912746012-40780714-8c0b-49fb-a732-bb7a624fda1f.png#align=left&display=inline&height=756&margin=%5Bobject%20Object%5D&name=7.png&originHeight=756&originWidth=745&size=23539&status=done&style=none&width=745)




点击：开启此虚拟机，然后按如下截图安装
语言选择English

![8.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608912803990-7e391ad8-fb5b-4fc1-8198-28445f548028.png#align=left&display=inline&height=600&margin=%5Bobject%20Object%5D&name=8.png&originHeight=600&originWidth=800&size=124836&status=done&style=none&width=800)

DATE & TIME选择Asia，Shanghai

![9.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608912805359-ad4bfaaa-eb7f-4edc-b001-a35292d62146.png#align=left&display=inline&height=630&margin=%5Bobject%20Object%5D&name=9.png&originHeight=630&originWidth=798&size=121141&status=done&style=none&width=798)

Software Selection 这里，选择Minimal Install

![10.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608912807013-818263c1-ca3e-41cb-b4b9-6b21d1b8ce80.png#align=left&display=inline&height=638&margin=%5Bobject%20Object%5D&name=10.png&originHeight=638&originWidth=808&size=123669&status=done&style=none&width=808)

![11.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608912808398-317f543d-dd88-47c0-91e6-c3c34f01fe47.png#align=left&display=inline&height=632&margin=%5Bobject%20Object%5D&name=11.png&originHeight=632&originWidth=802&size=58612&status=done&style=none&width=802)

KDUMP这里，Enable kdump这个选项不要勾选

![12.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608912809479-0426ff34-b499-4110-a98b-21276e42ca15.png#align=left&display=inline&height=662&margin=%5Bobject%20Object%5D&name=12.png&originHeight=662&originWidth=817&size=122310&status=done&style=none&width=817)

![13.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608912810823-ebd61d0b-afad-46e2-87fa-a3c263dac9be.png#align=left&display=inline&height=621&margin=%5Bobject%20Object%5D&name=13.png&originHeight=621&originWidth=803&size=65835&status=done&style=none&width=803)

NETWORK & HOST NAME这里，把Ethernet开关打开

![14.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608912811959-a4b26c98-a508-4d21-98c8-70e20021948e.png#align=left&display=inline&height=640&margin=%5Bobject%20Object%5D&name=14.png&originHeight=640&originWidth=834&size=129708&status=done&style=none&width=834)

![15.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608912813235-e5f8c572-f460-4f3c-bf20-db92dd497d75.png#align=left&display=inline&height=668&margin=%5Bobject%20Object%5D&name=15.png&originHeight=668&originWidth=840&size=88566&status=done&style=none&width=840)

设置Root密码

![16.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608912814385-dce299f9-eea7-4cd5-a771-aa1ee2421f52.png#align=left&display=inline&height=643&margin=%5Bobject%20Object%5D&name=16.png&originHeight=643&originWidth=815&size=197436&status=done&style=none&width=815)

点击USER CREATION，新建一个管理员账户

![17.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608912815461-e5cac508-a863-428e-9667-86cdff20186f.png#align=left&display=inline&height=610&margin=%5Bobject%20Object%5D&name=17.png&originHeight=610&originWidth=812&size=67750&status=done&style=none&width=812)**

然后系统会自动安装，安装好了以后，有个Reboot按钮，点击Reboot按钮，即可，Linux安装完成


## 设置HostName
```bash
hostnamectl set-hostname sec
```
使用以上命令，可以把Host Name设置为sec
## 网络配置


1. 找到网卡位置：
```bash
cd /etc/sysconfig/network-scripts/
```

2. 配置网卡协议
```bash
vim ifcfg-ens33
```


注：如无vim，可以执行
```bash
yum install -y vim
```
安装Vim


然后再执行：
```bash
vim ifcfg-ens33
```
删除UUID这一行
![19.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608913225772-cd0cb721-a88a-4bb6-a89b-203172976434.png#align=left&display=inline&height=484&margin=%5Bobject%20Object%5D&name=19.png&originHeight=484&originWidth=702&size=20830&status=done&style=none&width=702)


重启网卡服务


```bash
service network restart
```


测试：


ping www.baidu.com 查看是否有数据接收到。


## 快照与克隆


克隆之前，先打快照


1. 关闭虚拟机
1. 在节点这里，选择快照->快照管理器



![](https://cdn.nlark.com/yuque/0/2020/png/757806/1588602217391-024b5957-a05c-44d8-88cf-6bf5b8d462ca.png#align=left&display=inline&height=484&margin=%5Bobject%20Object%5D&originHeight=484&originWidth=498&status=done&style=none&width=498)


![](https://cdn.nlark.com/yuque/0/2020/png/757806/1588602249181-10bce7a3-1f2f-4a74-a7d9-81b7e193e87e.png#align=left&display=inline&height=550&margin=%5Bobject%20Object%5D&originHeight=550&originWidth=739&status=done&style=none&width=739)


为快照设置一个名字，假设为base，


![](https://cdn.nlark.com/yuque/0/2020/png/757806/1588602264236-9fbc5596-994e-49c7-ad32-06c3946f421f.png#align=left&display=inline&height=272&margin=%5Bobject%20Object%5D&originHeight=272&originWidth=436&status=done&style=none&width=436)


![](https://cdn.nlark.com/yuque/0/2020/png/757806/1588602313478-4b6e322c-f5c8-4d0d-8cec-5531105cdd6b.png#align=left&display=inline&height=550&margin=%5Bobject%20Object%5D&originHeight=550&originWidth=732&status=done&style=none&width=732)


然后开始克隆，在需要克隆节点上右键：克隆

![22.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608913346693-afc53602-5603-439b-88ed-39c3de37176f.png#align=left&display=inline&height=434&margin=%5Bobject%20Object%5D&name=22.png&originHeight=434&originWidth=524&size=11851&status=done&style=none&width=524)

![23.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608913347571-10bd5609-dfc2-4a72-b3d0-686fc4b67347.png#align=left&display=inline&height=434&margin=%5Bobject%20Object%5D&name=23.png&originHeight=434&originWidth=524&size=13824&status=done&style=none&width=524)

**这里选择创建链接克隆**可以节省资源，为克隆的虚拟机设置一个名称，假设叫：node02

克隆完毕




## 永久关闭SELinux的方法
```bash
vim /etc/selinux/config
```
把SELinux设置为disabled

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1608913578583-214cb33d-ede1-4904-a3ef-257602812e4c.png#align=left&display=inline&height=217&margin=%5Bobject%20Object%5D&name=image.png&originHeight=217&originWidth=738&size=17487&status=done&style=none&width=738)

## 配置ssh密钥连接Linux


首先Win10系统上需要有OpenSSH，像这样：终端输入ssh


![](https://cdn.nlark.com/yuque/0/2020/png/757806/1583580986229-a39ca5f4-a6df-43c1-9e57-e85003836fa0.png#align=left&display=inline&height=136&margin=%5Bobject%20Object%5D&originHeight=163&originWidth=689&status=done&style=none&width=576#align=left&display=inline&height=163&margin=%5Bobject%20Object%5D&originHeight=163&originWidth=689&status=done&style=none&width=689)


这样就是有的（好像Win10 1809+默认就是有的）。
然后生成密钥对：
ssh-keygen -t rsa
接着按提示信息可根据个人需求选择，这里是默认（连续三个回车即可）。
生成的密钥对默认保存在当前用户的根目录下的.ssh目录中（C:\Users\username.ssh）：


![](https://cdn.nlark.com/yuque/0/2020/png/757806/1583580986262-866d2d74-0511-4d07-b535-42f34c103bd8.png#align=left&display=inline&height=128&margin=%5Bobject%20Object%5D&originHeight=153&originWidth=691&status=done&style=none&width=576#align=left&display=inline&height=153&margin=%5Bobject%20Object%5D&originHeight=153&originWidth=691&status=done&style=none&width=691)


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


![](https://cdn.nlark.com/yuque/0/2020/png/757806/1583580986286-d1deb211-ea47-403c-9ffb-048f2a97373f.png#align=left&display=inline&height=507&margin=%5Bobject%20Object%5D&originHeight=507&originWidth=504&status=done&style=none&width=504#align=left&display=inline&height=507&margin=%5Bobject%20Object%5D&originHeight=507&originWidth=504&status=done&style=none&width=504)


## Linux的命令


分为内部命令和外部命令 内部命令（Shell自带的命令）


![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588604727521-7fce2dd3-d1f9-4bc5-95ac-7071c77c78d7.png#align=left&display=inline&height=52&margin=%5Bobject%20Object%5D&name=image.png&originHeight=52&originWidth=275&size=3550&status=done&style=none&width=275#align=left&display=inline&height=52&margin=%5Bobject%20Object%5D&originHeight=52&originWidth=275&status=done&style=none&width=275)


外部命令（不是Shell自带的命令，由用户安装的）


![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588604749548-b6dde106-d9d3-4529-95ff-f9891a6c0bca.png#align=left&display=inline&height=59&margin=%5Bobject%20Object%5D&name=image.png&originHeight=59&originWidth=300&size=3679&status=done&style=none&width=300#align=left&display=inline&height=59&margin=%5Bobject%20Object%5D&originHeight=59&originWidth=300&status=done&style=none&width=300)


查看命令是一个什么类型的文件


![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588604859481-c0c12d62-f6b8-448c-a17f-97024f9785ae.png#align=left&display=inline&height=74&margin=%5Bobject%20Object%5D&name=image.png&originHeight=74&originWidth=773&size=9768&status=done&style=none&width=773#align=left&display=inline&height=74&margin=%5Bobject%20Object%5D&originHeight=74&originWidth=773&status=done&style=none&width=773)


查看ifconfig命令在哪个位置


```shell
whereis ifconfig
```


什么是Shell? bash shell，就是一个程序，就是Linux系统安装的一个软件


root/密码写对后，直接进入bash shell软件


![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588605290609-77801687-5dca-4abf-8eaf-483e70d72e06.png#align=left&display=inline&height=294&margin=%5Bobject%20Object%5D&name=image.png&originHeight=294&originWidth=680&size=84812&status=done&style=none&width=680#align=left&display=inline&height=294&margin=%5Bobject%20Object%5D&originHeight=294&originWidth=680&status=done&style=none&width=680)


![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588605354987-f9976149-de96-4db0-8e20-28c521255c87.png#align=left&display=inline&height=78&margin=%5Bobject%20Object%5D&name=image.png&originHeight=78&originWidth=276&size=23804&status=done&style=none&width=276#align=left&display=inline&height=78&margin=%5Bobject%20Object%5D&originHeight=78&originWidth=276&status=done&style=none&width=276)

echo $PATH

![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588605392931-c4e9cc67-60a5-46ec-9490-5b2c1080ebab.png#align=left&display=inline&height=41&margin=%5Bobject%20Object%5D&name=image.png&originHeight=41&originWidth=514&size=3691&status=done&style=none&width=514#align=left&display=inline&height=41&margin=%5Bobject%20Object%5D&originHeight=41&originWidth=514&status=done&style=none&width=514)


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
- 



变量的默认作用范围
默认自己的shell进程中


变量的导出，让子进程获得父进程的变量值


- export 变量名
变量的删除
- unset 变量名



环境变量：每个Shell打开都可以获得的变量


- set和env命令
- $? 上一条命令是否正确执行（正确：0， 错误：1）
- 
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
> su - 用户名  login shell 所有都可以执行
> su 用户名 nologin shell  /bashrc, /etc/bashrc



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


![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588638734029-d7f3e8d9-e5b6-42ab-ad0f-bfa9d99e4541.png#align=left&display=inline&height=775&margin=%5Bobject%20Object%5D&name=image.png&originHeight=775&originWidth=807&size=217381&status=done&style=none&width=807#align=left&display=inline&height=775&margin=%5Bobject%20Object%5D&originHeight=775&originWidth=807&status=done&style=none&width=807)
除了/boot的数据，其他目录下的数据都存在了sda3里面了


/var 可变化的文件，比如：日志文件，数据文件


更多文件类型说明见：
![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1588639527449-93861280-c798-4615-ad30-2a992ffa29a1.png#align=left&display=inline&height=604&margin=%5Bobject%20Object%5D&name=image.png&originHeight=604&originWidth=687&size=223193&status=done&style=none&width=687#align=left&display=inline&height=604&margin=%5Bobject%20Object%5D&originHeight=604&originWidth=687&status=done&style=none&width=687)


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


[Linux预习资料](https://pan.baidu.com/s/1qLY7x29EtZO-uz3a06QzFQ)  提取码：7w30

[CentOS6.x升级到CentOS7.x的注意事项视频](https://pan.baidu.com/s/1_M0ADqa8LeHrJJTCwTDUpA)  提取码: yhfd 

[Linux内核设计与实现](https://book.douban.com/subject/6097773/)


[极客时间-Linux实战技能100讲](https://time.geekbang.org/course/intro/100029601)
