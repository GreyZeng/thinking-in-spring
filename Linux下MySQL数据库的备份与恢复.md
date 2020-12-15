# Linux下MySQL数据库的备份与恢复

作者：[Grey](https://www.cnblogs.com/greyzeng/)


原文地址：

[Github](https://github.com/GreyZeng/articles/blob/master/Linux%E4%B8%8BMySQL%E6%95%B0%E6%8D%AE%E5%BA%93%E7%9A%84%E5%A4%87%E4%BB%BD%E4%B8%8E%E6%81%A2%E5%A4%8D.md)

[语雀](https://www.yuque.com/greyzeng/uzfhep/bwav3b)

[博客园](https://www.cnblogs.com/greyzeng/p/14139141.html)

## 基于版本

- MySQL5.7
- Deepin Linux 15.11
- xtrabackup-2.4.18

## 定时备份脚本

### 前置工作

准备一个需要备份的数据库，假设这个数据库名称为cargo，示例脚本如下

```sql
CREATE DATABASE IF NOT EXISTS `cargo`;
USE `cargo`;

CREATE TABLE IF NOT EXISTS `b_gen` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `b_gen` (`id`, `name`) VALUES
	(1, 'SJA1'),
	(2, 'SJA2');

CREATE TABLE IF NOT EXISTS `b_org` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `b_org` (`id`, `name`) VALUES
	(1, 'SJA'),
	(2, 'ITC');
```

准备一个用于dump数据库的用户，授予以下权限：

```sql
-- 创建用户
create user db_user@'%' identified by 'db_pass'; 
-- 授权
grant select,show view,lock tables,trigger on cargo.* to db_user@'%' identified by 'db_pass';
flush privileges;
```

创建存放脚本的目录

```shell
mkdir /data/backup/mysql
```

脚本目录结构

- db_bk.sh →主程序
- check_dir.sh→验证mysql的相关目录是否存在
- options.conf→全局变量和相关配置

db_bk.sh

```shell
#!/bin/bash
DIRNAME=$0
if [ "${DIRNAME:0:1}" = "/" ];then
    CURDIR=`dirname $DIRNAME`
else
    CURDIR="`pwd`"/"`dirname $DIRNAME`"
fi
echo $CURDIR
# 定义全局变量
. $CURDIR/options.conf
# 检查相关目录
. $CURDIR/check_dir.sh
 
DBname=$1
LogFile=${backup_dir}/db.log
DumpFile=${backup_dir}/DB_${DBname}_$(date +%Y%m%d_%H).sql
NewFile=${backup_dir}/DB_${DBname}_$(date +%Y%m%d_%H).tgz
OldFile=${backup_dir}/DB_${DBname}_$(date +%Y%m%d --date="${expired_days} days ago")*.tgz
 
[ ! -e "${backup_dir}" ] && mkdir -p ${backup_dir}
 
DB_tmp=$(${db_install_dir}/bin/mysql -u${dbdumpuser} -p${dbdumppwd} -e "show databases\G" | grep ${DBname})
[ -z "${DB_tmp}" ] && {
    echo "[${DBname}] not exist" >>${LogFile}
    exit 1
}
 
if [ -n "$(ls ${OldFile} 2>/dev/null)" ]; then
    rm -f ${OldFile}
    echo "[${OldFile}] Delete Old File Success" >>${LogFile}
else
    echo "[${OldFile}] Delete Old Backup File" >>${LogFile}
fi
 
if [ -e "${NewFile}" ]; then
    echo "[${NewFile}] The Backup File is exists, Can't Backup" >>${LogFile}
else
    ${db_install_dir}/bin/mysqldump -u${dbdumpuser} -p${dbdumppwd} --databases ${DBname} >${DumpFile}
    pushd ${backup_dir} >/dev/null
    tar czf ${NewFile} ${DumpFile##*/} >>${LogFile} 2>&1
    echo "[${NewFile}] Backup success " >>${LogFile}
    rm -f ${DumpFile}
    popd >/dev/null
fi
```

并赋予可执行权限

```shell
chmod u+x db_bk.sh
```

创建备份后的数据库文件的存放目录：

```shell
mkdir /data/backup/mysql/backup_files
```

options.conf

```shell
# mysql 的安装路径，你可以通过以下SQL查看
# select @@basedir as basePath from dual ; show variables like '%basedir%';
mysql_install_dir=/usr/local/mysql
# mysql的数据存储路径，你可以通过以下SQL查看
# select @@datadir as dataPath from dual ;show variables Like '%datadir%';
mysql_data_dir=/data/mysql
dbdumpuser=db_user
dbdumppwd=db_pass

# Backup Dest directory, change this if you have someother location
backup_dir=/data/backup/mysql/backup_files

# How many days before the backup directory will be removed
expired_days=5
```

并赋予可执行权限

```shell
chmod u+x options.conf
```

check_dir.sh

```shell
#!/bin/bash
 
# check MySQL dir
# [ -d "${mysql_install_dir}/support-files" ] && { db_install_dir=${mysql_install_dir}; db_data_dir=${mysql_data_dir}; }
{
    db_install_dir=${mysql_install_dir}
    db_data_dir=${mysql_data_dir}
}
```

并赋予可执行权限

```shell
chmod u+x check_dir.sh
```

将这个脚本加到定时任务中：

```shell
crontab -e
```

编辑定时任务文件，增加以下一行，cron表达式意思为：每小时执行一次：

```shell
*/60 * * * * /bin/bash /data/backup/mysql/db_bk.sh cargo
```

## 定时清理脚本

在/data/backup/mysql/backup_files目录下创建：deleteLegacy.sh

```shell
#!/bin/bash
for file in `find /data/backup/mysql/backup_files/ -type f -name "*"`
    do
       let expired_time=$[1*24*60*60]        #此处定义文件的过期时间1天
       let currentDate=`date +%s`            #获取系统时间，所以时间格式为秒
       let modifyDate=$(stat -c %Y $file)    #获取文件修改时间
       let existTime=$[$currentDate-$modifyDate]     #对比时间，算出日志存在时间
       if [ $existTime -gt $expired_time ];
          then
            rm -rf $file    #删除文件
       fi
done
```

并赋予可执行权限

```shell
chmod u+x deleteLegacy.sh
```

加入定时任务

```shell
crontab -e
```

编辑定时任务文件，增加以下一行，cron表达式意思为：每天凌晨1点执行一次：

```shell
00 01 * * * /bin/sh /data/backup/mysql/backup_files/deleteLegacy.sh
```

## 定时同步脚本

定时同步cargo数据库到一个新的数据库（需要提前先建好这个数据库，假设名字为：cargo_backup）

```sql
CREATE DATABASE IF NOT EXISTS `cargo_backup`;
```

**将之前新建的db_user用户，赋予cargo_backup的所有权限，同时需要设置db_user的Global privileges的SUPER权限（否则导入视图的时候会有问题**

```sql
grant all privileges on cargo_backup.* to db_user@'%' identified by 'db_pass';
grant SUPER on *.* to db_user@'%';
flush privileges;
```

创建同步SQL目录

```shell
mkdir /data/backup/mysql/mysqlsync
```

脚本参考

mysql_sync.sh

```shell
#!/bin/bash
# MySQL数据库
 
 
# 创建一个同步专用用户(赋予select,show view, trrigger, lock tables权限)
# 对于备份的目标数据库有所有权限
# 要设置Global privileges的SUPER权限
DB_USER="db_user"
DB_PASS="db_pass"
DB_HOST="localhost"
 
# 需要备份的数据库名称
DB_FROM="cargo"
DB_TO="cargo_backup"
 
BIN_DIR="/usr/local/mysql/bin"
 
SYNC_DIR="/data/backup/mysql/mysqlsync"
 
$BIN_DIR/mysqldump -u$DB_USER -p$DB_PASS -h$DB_HOST $DB_FROM > $SYNC_DIR/sync.sql
$BIN_DIR/mysql -u$DB_USER -p$DB_PASS -h$DB_HOST $DB_TO < $SYNC_DIR/sync.sql
```

赋予可执行权限

```shell
chmod u+x mysql_sync.sh
```

加入定时任务

```shell
crontab -e
```

编辑定时任务文件，增加以下一行，cron表达式意思为：每天凌晨1点执行一次：

```shell
00 01 * * * /bin/bash /data/backup/mysql/backup_files/mysql_sync.sh
```



## 采用mysqlpump备份数据库操作

**mysqlpump主要用于备份整个sql执行过程，针对误操作的情况，我们可以拿mysqlpump中的操作记录，逐个执行，一直到操作有误的那个操作停止，这样就可以撤销上一次有误的操作，这种方式的缺点是恢复的时间比较长，优点是可以控制到每一次执行的操作记录**

```shell
ln -s /usr/local/mysql/bin/mysqlpump /usr/bin
```

```sql
grant all privileges on *.* to mysqlpump@'%' identified by 'mysqlpump';
-- grant reload,lock tables,replication client,create tablespace,process,super on *.* to mysqlpump@'%' identified by 'mysqlpump';
FLUSH PRIVILEGES;
```

备份，假设要备份的数据库为t，执行

```bash
mysqlpump -umysqlpump -pmysqlpump --databases t >t.sql
```

查看备份后的文件：

t.sql

```sql
-- Dump created by MySQL pump utility, version: 5.7.29, Linux (x86_64)
-- Dump start time: Tue Feb 25 19:44:18 2020
-- Server version: 5.7.29

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE;
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET @@SESSION.SQL_LOG_BIN= 0;
SET @OLD_TIME_ZONE=@@TIME_ZONE;
SET TIME_ZONE='+00:00';
SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT;
SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS;
SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION;
SET NAMES utf8mb4;
CREATE DATABASE /*!32312 IF NOT EXISTS*/ `t` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
CREATE TABLE `t`.`tt` (
`t` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
;
CREATE TABLE `t`.`x` (
`Column 1` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
;
INSERT INTO `t`.`tt` VALUES (2),(2),(2);
SET TIME_ZONE=@OLD_TIME_ZONE;
SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT;
SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS;
SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET SQL_MODE=@OLD_SQL_MODE;
-- Dump end time: Tue Feb 25 19:44:18 2020
```

## 采用Binlog恢复数据库

> 可以查看整个数据库的操作记录，对于误操作的记录，可以先还原为最近的一次备份，然后将mysqlpump后的sql逐句执行，一直到执行错误的那条语句（忽略之）


控制台用root登录

```sql
mysql -uroot -p
```

```sql
show variables like 'binlog_format'
```

```
+---------------+-------+
| Variable_name | Value |
+---------------+-------+
| binlog_format | MIXED |
+---------------+-------+
```

binlog的配置在/etc/my.cnf中配置, 若要配置，需要先停数据库服务

```
service mysql stop
```

然后在my.cnf配置这两个参数

```
log_bin = mysql-bin
binlog_format = mixed
```

重启数据库

```
service mysql start
```

接下来我们模拟几次操作,我在demo数据库下建了tsdtas这个表

```sql
-- 正常操作
INSERT INTO `demo`.`tsdtas` (`mm`,`x`) VALUES ('929','1');
INSERT INTO `demo`.`tsdtas` (`mm`,`x`) VALUES ('929','1');
INSERT INTO `demo`.`tsdtas` (`mm`,`x`) VALUES ('929','1');
-- 错误操作
DELETE FROM `demo`.`tsdtas`;
-- 正常操作
INSERT INTO `demo`.`tsdtas` (`mm`,`x`) VALUES ('929','1');
INSERT INTO `demo`.`tsdtas` (`mm`,`x`) VALUES ('929','1');
```

控制台用root登录

```sql
mysql -uroot -p
```

执行

```
flush logs;
```

在mysql的数据目录下（一般为：/data/mysql) 找到最新的binlog(格式为：mysql-bin.00000x)， 在mysql控制台中

执行：

```
show binlog events in"mysql-bin.000008";
```

即可查看所有操作记录

```
MySQL [(none)]> show binlog events in"mysql-bin.000008";
+------------------+------+----------------+-----------+-------------+-----------------------------------------------------------------------+
| Log_name         | Pos  | Event_type     | Server_id | End_log_pos | Info                                                                  |
+------------------+------+----------------+-----------+-------------+-----------------------------------------------------------------------+
| mysql-bin.000008 |    4 | Format_desc    |         1 |         123 | Server ver: 5.7.29-log, Binlog ver: 4                                 |
| mysql-bin.000008 |  123 | Previous_gtids |         1 |         154 |                                                                       |
| mysql-bin.000008 |  154 | Anonymous_Gtid |         1 |         219 | SET @@SESSION.GTID_NEXT= 'ANONYMOUS'                                  |
| mysql-bin.000008 |  219 | Query          |         1 |         298 | BEGIN                                                                 |
| mysql-bin.000008 |  298 | Query          |         1 |         429 | use `demo`; INSERT INTO `demo`.`tsdtas` (`mm`,`x`) VALUES ('929','1') |
| mysql-bin.000008 |  429 | Xid            |         1 |         460 | COMMIT /* xid=282 */                                                  |
| mysql-bin.000008 |  460 | Anonymous_Gtid |         1 |         525 | SET @@SESSION.GTID_NEXT= 'ANONYMOUS'                                  |
| mysql-bin.000008 |  525 | Query          |         1 |         604 | BEGIN                                                                 |
| mysql-bin.000008 |  604 | Query          |         1 |         735 | use `demo`; INSERT INTO `demo`.`tsdtas` (`mm`,`x`) VALUES ('929','1') |
| mysql-bin.000008 |  735 | Xid            |         1 |         766 | COMMIT /* xid=284 */                                                  |
| mysql-bin.000008 |  766 | Anonymous_Gtid |         1 |         831 | SET @@SESSION.GTID_NEXT= 'ANONYMOUS'                                  |
| mysql-bin.000008 |  831 | Query          |         1 |         910 | BEGIN                                                                 |
| mysql-bin.000008 |  910 | Query          |         1 |        1041 | use `demo`; INSERT INTO `demo`.`tsdtas` (`mm`,`x`) VALUES ('929','1') |
| mysql-bin.000008 | 1041 | Xid            |         1 |        1072 | COMMIT /* xid=286 */                                                  |
| mysql-bin.000008 | 1072 | Anonymous_Gtid |         1 |        1137 | SET @@SESSION.GTID_NEXT= 'ANONYMOUS'                                  |
| mysql-bin.000008 | 1137 | Query          |         1 |        1216 | BEGIN                                                                 |
| mysql-bin.000008 | 1216 | Query          |         1 |        1317 | use `demo`; DELETE FROM `demo`.`tsdtas`                               |
| mysql-bin.000008 | 1317 | Xid            |         1 |        1348 | COMMIT /* xid=288 */                                                  |
| mysql-bin.000008 | 1348 | Anonymous_Gtid |         1 |        1413 | SET @@SESSION.GTID_NEXT= 'ANONYMOUS'                                  |
| mysql-bin.000008 | 1413 | Query          |         1 |        1492 | BEGIN                                                                 |
| mysql-bin.000008 | 1492 | Query          |         1 |        1623 | use `demo`; INSERT INTO `demo`.`tsdtas` (`mm`,`x`) VALUES ('929','1') |
| mysql-bin.000008 | 1623 | Xid            |         1 |        1654 | COMMIT /* xid=290 */                                                  |
| mysql-bin.000008 | 1654 | Anonymous_Gtid |         1 |        1719 | SET @@SESSION.GTID_NEXT= 'ANONYMOUS'                                  |
| mysql-bin.000008 | 1719 | Query          |         1 |        1798 | BEGIN                                                                 |
| mysql-bin.000008 | 1798 | Query          |         1 |        1929 | use `demo`; INSERT INTO `demo`.`tsdtas` (`mm`,`x`) VALUES ('929','1') |
| mysql-bin.000008 | 1929 | Xid            |         1 |        1960 | COMMIT /* xid=292 */                                                  |
| mysql-bin.000008 | 1960 | Rotate         |         1 |        2007 | mysql-bin.000009;pos=4                                                |
+------------------+------+----------------+-----------+-------------+-----------------------------------------------------------------------+
```

在这个操作记录中，把DELETE语句重新删掉，其他语句重新执行即可恢复。

## 采用xtrabackup备份和恢复数据库

**Important: With the introduction of Percona XtraBackup 8.0, Percona XtraBackup 2.4 will continue to support MySQL and Percona Server 5.6 and 5.7 databases. Due to the new MySQL redo log and data dictionary formats the Percona XtraBackup 8.0.x versions will only be compatible with MySQL 8.0.x and the upcoming Percona Server for MySQL 8.0.x**

### 安装Xtrabackup

```shell
tar zxvf percona-xtrabackup-2.4.18-Linux-x86_64.libgcrypt20.tar.gz
cd percona-xtrabackup-2.4.18-Linux-x86_64/bin
cp xtrabackup /usr/bin/xtrabackup
cp innobackupex /usr/bin/innobackupex
```

### 创建用户并授权

```shell
# 用户授权（注：可以限制访问ip，如本机访问，可以将%改成localhost）
create user xtrabackup@'%' identified by 'xtrabackup';
grant reload,lock tables,replication client,create tablespace,process,super on *.* to xtrabackup@'%' identified by 'xtrabackup';
# 这里可以指定具体数据库的增删改的权限
grant all privileges on *.* to xtrabackup@'%'  identified by 'xtrabackup' ;
FLUSH PRIVILEGES;
```

### 创建备份目录

```
mkdir /data/xtrabackup/
```

### 全量备份数据库

```
# 查看my.cnf位置：mysql --help | grep 'Default options' -A 1
innobackupex --defaults-file=/etc/my.cnf --user=xtrabackup --password=xtrabackup --socket=/tmp/mysql.sock  /data/xtrabackup/
```

执行完毕后，在/data/xtrabackup下会有一个以时间戳命名的文件夹：类似：2020-02-24_14-42-16

### 删除数据库

```shell
# 停止数据库服务
service mysql stop
# 删除mysql的data目录：select @@datadir as dataPath from dual ;
# 假设data目录为：/data/mysql
cd /data
mv mysql/ mysql_bak/
mkdir mysql
```

利用 --apply-log来回滚未提交的事务及同步已经提交的事务至数据文件，使数据文件处于一致性状态。

```
innobackupex --apply-log /data/xtrabackup/2020-02-24_14-42-16/
```

### 恢复

```
innobackupex  --defaults-file=/etc/my.cnf --copy-back --rsync /data/xtrabackup/2020-02-24_14-42-16/
```

配置mysql用户的数据目录权限

```
chmod -R mysql.mysql /data
```

启动数据库

```shell
service mysql start
```

### 增量备份

> 我们基于之前的全量备份，做增量备份操作。

首先，模拟增量数据，示例SQL


```sql
INSERT INTO `tt` (`t`) VALUES
	(5434),
	(2),
	(3);
```

```shell
innobackupex --defaults-file=/etc/my.cnf --user=xtrabackup --password=xtrabackup --socket=/tmp/mysql.sock  --incremental-basedir=/data/xtrabackup/2020-02-24_14-42-16 --incremental /data/xtrabackup/
```

此时，/data/xtrabackup下会生成一个时间戳文件夹，例如：2020-02-25_14-55-31

这个文件夹是2020-02-24_14-42-16这个备份的增量备份

### 删除数据库

```shell
# 停止数据库服务
service mysql stop
# 删除mysql的data目录：select @@datadir as dataPath from dual ;
# 假设data目录为：/data/mysql
cd /data
mv mysql/ mysql_bak/
mkdir mysql
```

### 增量恢复

```
innobackupex --apply-log --redo-only /data/xtrabackup/2020-02-24_14-42-16
innobackupex --apply-log --redo-only /data/xtrabackup/2020-02-24_14-42-16 --incremental-dir=/data/xtrabackup/2020-02-25_14-55-31
innobackupex  --defaults-file=/etc/my.cnf --copy-back --rsync /data/xtrabackup/2020-02-24_14-42-16/
```

配置mysql用户的数据目录权限

```
chown -R mysql.mysql /data
```

启动数据库

```shell
service mysql start
```
