# Git推送到多个远程仓库

作者：[Grey](https://www.cnblogs.com/greyzeng/)


原文地址：

[Github](https://github.com/GreyZeng/articles/blob/master/Git%E6%8E%A8%E9%80%81%E5%88%B0%E5%A4%9A%E4%B8%AA%E8%BF%9C%E7%A8%8B%E4%BB%93%E5%BA%93.md)

[语雀](https://www.yuque.com/greyzeng/uzfhep/ycoqn4)

[博客园](https://www.cnblogs.com/greyzeng/p/11620839.html)

### 准备工作

在[码云](https://gitee.com/)和[Github](https://github.com/)上分别新建两个**不包括任何文件的空仓库**(若是两个已经有文件的仓库，请参见[关联已经存在的项目](#jump))

- [https://github.com/GreyZeng/article.git](https://github.com/GreyZeng/article.git)

- [https://gitee.com/greyzeng/article.git](https://gitee.com/greyzeng/article.git)


在本地新建一个article的文件夹，在该文件夹目录下执行：

```sh
git init
```

然后执行

```sh
git remote add origin https://gitee.com/greyzeng/article.git
git remote set-url --add origin https://github.com/GreyZeng/article.git
```

### 测试一下

在本地仓库article文件夹中增加一个文件`readme.txt`，依次执行

```sh
git add readme.txt
git commit -m "add readme.txt"
git push -u origin master
```

即可将`readme.txt`推送到github和gitee两个仓库中。

### 关联已经存在的项目

用以上article仓库进行说明

#### 准备工作

- [https://github.com/GreyZeng/article.git](https://github.com/GreyZeng/article.git)

- [https://gitee.com/greyzeng/article.git](https://gitee.com/greyzeng/article.git)


两个仓库已经有一个readme.txt文件

在本地新建一个article的文件夹，在该文件夹目录下执行：

```sh
git init
```

然后执行

```sh
git remote add origin https://gitee.com/greyzeng/article.git
git remote set-url --add origin https://github.com/GreyZeng/article.git
```

从默认仓库中fetch下文件

```sh
git pull origin master
```

#### 测试一下

在本地仓库article文件夹中增加一个文件`readme2.txt`，依次执行

```sh
git add readme2.txt
git commit -m "add readme2.txt"
git push -u origin master
```

即可将`readme2.txt`推送到github和gitee两个仓库中。
