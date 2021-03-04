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

#### 环境搭建：

##### 复杂类型

```java
public class Address {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
```

##### 测试对象

```java
    //测试样例  value | ref | list | map | set  | bean | idref | props | null
    private String name;   //value
    private Address address;   //ref
    private String[] books;     //idref 数组
    private List<String> hobbys;  //list
    private Map<String,String> card;   //map
    private Set<String> games;  //set
    private String wife;        //null  空指针
    private Properties info;   //props
```

##### beans.xml

```xml
<bean id="address" class="com.yuan.pojo.Address">
    <property name="address" value="射阳"/>
</bean>

<bean id="student" class="com.yuan.pojo.Student">
    <!--第一种  普通值注入-->
    <property name="name" value="value测试"/>

    <!--第二种 bean注入 ref-->
    <property name="address" ref="address"/>

    <!--第三种 数组注入-->
    <property name="books">
        <array>
            <value>红楼梦</value>
            <value>水浒传</value>
            <value>西游记</value>
            <value>三国志</value>
        </array>
    </property>

    <!--第四种 list注入-->
    <property name="hobbys">
        <list>
            <value>听歌</value>
            <value>鞋子</value>
            <value>加班</value>
        </list>
    </property>

    <!--第五种 map注入-->
    <property name="card">
        <map>
            <entry key="身份证" value="321789199511023597"/>
            <entry key="银行卡" value="010101010101010101"/>
        </map>
    </property>

    <!--第六种 set注入-->
    <property name="games">
        <set>
            <value>国正LOL</value>
            <value>美测LOL</value>
            <value>美正LOL</value>
        </set>
    </property>

    <!--第七种 null注入-->
    <property name="wife">
        <null/>
    </property>

    <!--第八种 properties注入-->
    <property name="info">
        <props>
            <prop key="driver">12456897</prop>
            <prop key="url">男</prop>
            <prop key="username">yuan</prop>
            <prop key="password">lei</prop>
        </props>
    </property>
</bean>
```

##### 测试类

```java
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.getName());
    }
```





### 3、拓展方式注入[第三方]

我们可以使用[P命名与C命名空间实现注入](http://r6d.cn/b9xr9)

##### 引入头部命名空间

```xml
xmlns:p="http://www.springframework.org/schema/p"
xmlns:c="http://www.springframework.org/schema/c"
```

##### 使用

```xml
<!--P命名空间注入，可以直接注入属性的值-->
<bean id="user" class="com.yuan.pojo.User" p:name="p-yuan" p:age="25"/>

<!--
        C-命名空间注入，通过构造器注入
        相比P命名，需要有参 与 无参方法
    -->
<bean id="user2" class="com.yuan.pojo.User" c:name="c-yuan" c:age="25" />
```

##### 测试

```java
@Test
public void test2(){
    ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");

    User user = context.getBean("user", User.class);
    User user2 = context.getBean("user2", User.class);
    System.out.println("P命名注入："+user.toString()+"  C命名："+user2.toString());
}
```

注意点：P命名与C命名空间不能直接使用，需要导入约束，即命名空间头部

#### 4、bean的作用域

| Scope                                                        | Description                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [singleton](https://docs.spring.io/spring-framework/docs/5.2.0.RELEASE/spring-framework-reference/core.html#beans-factory-scopes-singleton) | (Default) Scopes a single bean definition to a single object instance for each Spring IoC container. |
| [prototype](https://docs.spring.io/spring-framework/docs/5.2.0.RELEASE/spring-framework-reference/core.html#beans-factory-scopes-prototype) | Scopes a single bean definition to any number of object instances. |
| [request](https://docs.spring.io/spring-framework/docs/5.2.0.RELEASE/spring-framework-reference/core.html#beans-factory-scopes-request) | Scopes a single bean definition to the lifecycle of a single HTTP request. That is, each HTTP request has its own instance of a bean created off the back of a single bean definition. Only valid in the context of a web-aware Spring `ApplicationContext`. |
| [session](https://docs.spring.io/spring-framework/docs/5.2.0.RELEASE/spring-framework-reference/core.html#beans-factory-scopes-session) | Scopes a single bean definition to the lifecycle of an HTTP `Session`. Only valid in the context of a web-aware Spring `ApplicationContext`. |
| [application](https://docs.spring.io/spring-framework/docs/5.2.0.RELEASE/spring-framework-reference/core.html#beans-factory-scopes-application) | Scopes a single bean definition to the lifecycle of a `ServletContext`. Only valid in the context of a web-aware Spring `ApplicationContext`. |
| [websocket](https://docs.spring.io/spring-framework/docs/5.2.0.RELEASE/spring-framework-reference/web.html#websocket-stomp-websocket-scope) | Scopes a single bean definition to the lifecycle of a `WebSocket`. Only valid in the context of a web-aware Spring `ApplicationContext`. |

1、单例模式 singleton（spring默认机制）共用实例

```xml
<bean id="user2" class="com.yuan.pojo.User" c:name="c-yuan" c:age="25" scope="singleton"/>
```

2、原型模式 prototype 每次都新建实例

```xml
<bean id="user2-2" class="com.yuan.pojo.User" c:name="c-yuan" c:age="25" scope="prototype"/>
```

3、其余的的只能在web开发中使用到

# 5、Bean的自动装配

- 自动装配是Spring满足bean依赖的一种方式
- Spring会再上下文中自动寻找，自动给bean装配属性

### 在Spring中有三种装配方式

1. 在xml中显示的配置
2. 在java中显示配置
3. 隐式的自动装配bean【重要】

## 5.1、测试

1、环境搭建

- 人
- 狗
- 猫

### 5.2、byName 与 byType 自

```xml
<!--
        byName : 会自动在容器上下文中查找，和自己对象set方法后面的值对应的beanid
        byType : 会自动在容器上下文中查找，和自己对象属性类型相同的bean  注入的bean 中的id 都是可以省略的
    -->
<bean id="people" class="com.yuan.pojo.People" autowire="byName">
    <property name="name" value="yuan"/>
    <!--不自动装配 写的代码-->
    <!--<property name="dog" ref="dog"/>-->
    <!--<property name="cat" ref="cat"/>-->
</bean>
```

小结：

- byName，需要保证所有的bean的id唯一，且这个bean需要和自动注入的属性的`set方法`的**值**一致
- byType，需要保证所有的bean的class唯一，且这个bean需要和自动注入的属性的**类型**一致

## 5.3、使用注解开发

jdk1.5支持注解，Spring2.5支持

The introduction of annotation-based configuration raised the question of whether this approach is “better” than XML

使用注解须知

1. 导入约束：context约束
2. ==配置注解的支持：<context:annotation-config/>==

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">
    <!--开启注解的支持-->
    <context:annotation-config/>

</beans>
```

### `@Autowired`[常用]

直接在属性上使用,也可以在set方式上使用

可以不用编写set方法，前提是自动装配的属性在IOC(Spring)容器中存在，且符合名字byName

附加:

```xml
@Nullable 字段标记这个注解，说明这个字段可以为NULL    
public void setCat(@Nullable Cat cat) {
   this.cat = cat;
}
```

如果显示定义了 @Autowired 的required属性为false，说明这个对象可以为null，否则不允许为空

```
@Autowired(required = false)
private Cat cat;
```

如果@Autowired自动装配环境很复杂，自动装配无法通过一个注解完成的时候，我们可以使用@Qualifier(value="xxx")去配置@Autowired的使用，指定一个唯一的bean对象注入

### @Resource

先通过名字查找，再通过实例查找，都找不到时候会报错【更高级】

@Autowired与@Resource的区别：

- 都是用来自动装配，都可以放在属性字段上
- @Autowired通过byType方式实现，而且必须要求这个对象存在
- @Resource默认通过byName的方式实现，如果找不到名字，则通过byType实现！如果两个都找不到，会报错
- 执行顺序不同，@Autowaired通过byType的方式实现













