# 1、[Spring](https://www.bilibili.com/video/BV1WE411d7Dv?p=5)

## 1.1、前期准备

#### 学习[地址](https://docs.spring.io/spring-framework/docs/5.2.0.RELEASE/spring-framework-reference/core.html#spring-core)

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

![image-20210220110454696](readmeSpring.assets/image-20210220110454696.png)

### 控制反转

即：获得依赖对象的方式反转了，程序员获得了解放，用户获得了想要的自由调度的机会

| 主动权在程序员，每增加一种需要就要修改业务层                 | 主动权在用户，增加需要后用户选择调用                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![image-20210220110949568](readmeSpring.assets/image-20210220110949568.png) | ![image-20210220111005187](readmeSpring.assets/image-20210220111005187.png) |

![image-20210221105255301](readmeSpring.assets/image-20210221105255301.png)

#### 控制

谁来控制对象的创建？传统应用程序的对象是由程序本身控制创建的，使用Spring后，对象是由Spring来创建的。

#### 反转

程序本身不创建对象，变成被动的接收对象

#### 依赖注入

利用set方法来进行注入

#### IOC小结

IOC是一种编程思想，由主动的编程变成被动的接收

我们彻底不用在程序中去做改动，要实现不同的操作，只需要在xml配置文件中进行注册就可以

##### 所谓的IOC

一句话：对象由Spring创建、管理、装配

#### 思考：

Hello对象的属性是怎么设置的？

答案：是由Spring容器设置的

### 底层逻辑

![image-20210221100503293](readmeSpring.assets/image-20210221100503293.png)

### IOC创建对象的方式

beans.xml容器的配置<官网>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="..." class="...">  
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <bean id="..." class="...">
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <!-- more bean definitions go here -->

</beans>
```

1. 使用无参构造方法创建对象

2. 假设要使用有参构造方法创建对象

   1. 下标赋值

      ```java
      <bean id="user" class="com.yuan.pojo.User">
          <constructor-arg index="0" value="下标赋值"/>
      </bean>
      ```

   2. 参数类型  不建议使用

      ```java
      <bean id="user" class="com.yuan.pojo.User">
      	<constructor-arg type="java.lang.String" value="根据参数类型"/>
      </bean>
      ```

   3. 参数名

      ```java
      <bean id="user" class="com.yuan.pojo.User">
      	<constructor-arg name="name" value="直接通过参数名"/>
      </bean>
      ```

   总结：在配置文件加载的时候，容器中管理的对象就已经初始化了

# 3、Spring配置

### 别名

```xml
    <!--别名 两个名字都可使用-->
    <alias name="test" alias="test2"/>
```

### bean的配置

```xml
    <!--
    id : bean的唯一标识符，也就是相当于我们学习的对项目
    class : bean对象所对应的权限命名：包名+类型
    name : 也是别名,而且 name 可以同时取多个别名，比alias更高级,name 可以用 空格，都好，分号 来分割
    -->
    <bean id="test" class="com.yuan.pojo.UserSecondTest" name="test2 test3,test4;test5">
        <constructor-arg name="testName" value="测试第二个实例"/>
    </bean>
```

### import

一般用于团队开发，可以将多个配置文件<bean.xml>导入合并为一个

假设，现在项目中有多个人开发，这三个人复制不通的类开发，不同的类需要注册在不通的bean中

用import将所有人的beans.xml合并为一个总的

- 张三
- 李四
- 王五
- applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <import resource="beans.xml"/>
    <import resource="beans2.xml"/>
    <import resource="beans3.xml"/>
</beans>
```

使用的时候，直接使用总的配置就可以了

# 4、依赖注入

### 1、构造器注入

```xml
    <bean id="beanOne" class="x.y.ThingOne">
        <constructor-arg ref="beanTwo"/>
        <constructor-arg ref="beanThree"/>
    </bean>
```

### 2、Set方式注入[重点]

- 依赖注入：Set注入
  - 依赖：bean对象的创建依赖于容器
  - 注入：bean对象的所有属性，由容器来注入



### 3、拓展方式注入[第三方]











































