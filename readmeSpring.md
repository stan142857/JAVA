# 1、[Spring](https://www.bilibili.com/video/BV1WE411d7Dv?p=5)

## 1.1、前期准备

#### 了解：

- SSH :  Struct2 + Spring + Hibernate
- SSM : SpringMvc + Spring + Mybatis

下载：[点此](https://mvnrepository.com/artifact/org.springframework/spring-webmvc)

**依赖：**

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.3.4</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.3.4</version>
</dependency>

```

## 1.2、优点

- Spring是一个轻量级、非入侵式的框架
- 控制反转（IOC）,面向切面编程（AOP）
- 支持事务的处理，对框架整合的支持

> 总结一句话：Spring就是一个轻量级的控制反转（IOC）和面向切面编程（AOP）的框架

- Spring Boot

  - 一个快速开发的脚手架
  - 基于SpringBOOT可以快速开发单个微服务
  - 约定大于配置

- Spring Cloud

  - Spring Cloud是基于SpringBoot实现的

  

## 1.3、弊端

配置过多

# 2、IOC理论推导

1. UserDao接口
2. YserDaoImpl实现类
3. UserService业务接口
4. UserServiceImpl业务实现类

```java
    //用户实际调用的是业务层，dao层他们不需要接触
    UserService userService = new UserServiceImpl();

    //用户 新增需求，写在了dao层，此需要可能会影响原来的业务层代码，
    // 修改业务层service Userserviceimpl 代码为 set注入 来动态获取值
    //  之前，程序是主动创建对象，控制权在程序员手上，没新增一个就要新增dao层接口实现
    //  现在，使用了set注入，程序不再具有主动性，变成了被动的接受对象
    //从本质上解决问题，程序猿不需要去管理对象的创建
    //原本套路：扩展业务后，修改service层，但是现在只需要专注业务层
	//即控制反转
    ((UserServiceImpl)userService).setUserDao(new UserDaoMysqlImpl());
    userService.getUser();
```

```java
    //程序控制选择的对象，但是会导致后期大量修改
    //private UserDao userDao = new UserDaoImpl();
    //优化方案：
    //设置接口，用set方法进行动态实现值的注入
    private UserDao userDao ;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();
    }
```

![image-20210220110454696](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210220110454696.png)

### 控制反转

即：获得依赖对象的方式反转了，程序员获得了解放，用户获得了想要的自由调度的机会

| 主动权在程序员，每增加一种需要就要修改业务层                 | 主动权在用户，增加需要后用户选择调用                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![image-20210220110949568](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210220110949568.png) | ![image-20210220111005187](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210220111005187.png) |

