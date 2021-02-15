# 第一个[Mybatis](https://mybatis.org/mybatis-3/zh/getting-started.html)程序

参考视频点击[此处](https://www.bilibili.com/video/BV1NE411Q7Nx?p=2)

## 1. 搭建环境

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <!--父工程-->
    <groupId>com.yuan</groupId>
    <artifactId>Mybatis-Study</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>
    <modules>
        <module>mybatis-01</module>
    </modules>
    <!--第一步 搭建环境-->
    <!--导入依赖-->
    <dependencies>
        <!--mysql-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>
        <!--mybatis-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.2</version>
        </dependency>
        <!--junit 驱动-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
        </dependency>

    </dependencies>
</project>
```

如果无法导入依赖，需要在Settings - build,excution,de*** - Maven 下的user settings file 目录下导入maven的settings.xml

查找mirror，修改为阿里源

```xml
  <mirrors>
      <mirror>
      <id>alimaven</id>
      <mirrorOf>central</mirrorOf>
      <name>aliyun maven</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
    </mirror>
  </mirrors>
```



## 2. 创建一个模块

```java
    private static SqlSessionFactory sqlSessionFactory;
    
    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    既然有了sqlSessionFactory，我们就可以从中获取sqlSession的实例了
//    sqlSession完全包含了sql所需的所有方法
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
```



## 3.编写代码

### 实体类

```java
private int id;
    private String username;
    private String userpwd;
    private String hobby;
    private Date regtime;

    public User() {
    }

    public User(int id, String username, String userpwd, String hobby, Date regtime) {
        this.id = id;
        this.username = username;
        this.userpwd = userpwd;
        this.hobby = hobby;
        this.regtime = regtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userpwd='" + userpwd + '\'' +
                ", hobby='" + hobby + '\'' +
                ", regtime=" + regtime +
                '}';
    }
```



### Dao接口

```java
public interface UserDao {
    List<User> getUserList();
}
```

### 接口实现类

由原来的impl转换为mapper配置文件

```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!--namespace=绑定一个对应的Dao/Mapper接口-->
<mapper namespace="com.yuan.dao.UserDao">
    <select id="getUserList" resultType="com.yuan.pojo.User">
        select * from infomanage.userinfo;
    </select>
</mapper>
```



## 代码七步

> mybatisUtils.java  工具类 -   一次定义
>
> mybatis-config.xml   工具类需要配置文件   -   一次定义
>
> user.java   实体类 -   一次定义
>
> userMapper.java  实体类的接口  -   后期变更
>
> userMapper.xml  映射实体类的接口 -   后期变更
>
> userDaoTest.java  测试  -   后期变更
>
> pom.xml 加入maven的资源过滤  -   一次定义

### 资源过滤问题

Maven在dao层建立xml后，在目标文件夹中没有生效

[Maven由于他的约定大于配置，我们之后可能会遇到我们写的配置文件，无法被导出或者生效的问题](https://www.cnblogs.com/cherish-code/p/13577055.html)

```
    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.yml</include>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.yml</include>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
    </build>
```

## CURD 增删改查

### 1.编写接口 UserMapper.java

```java
    //查询全部用户
    List<User> getUserList();

    //根据ID查询用户
    User getUserById(int id);

    //插入用户
    int insertUser(User user);

    //修改用户
    int updateUser(User user);

    //删除用户
    int deleteUser(int id);
```

### 2.编写Mapper中的sql语句

```java
    <!--查询  无参-->
    <select id="getUserList" resultType="com.yuan.pojo.User">
        select * from infomanage.userinfo;
    </select>
    <!--查询 id为参-->
    <select id="getUserById" resultType="com.yuan.pojo.User" parameterType="int">
        select * from infomanage.userinfo where id = #{id};
    </select>
    <!--插入-->
    <insert id="insertUser" parameterType="com.yuan.pojo.User">
        insert into infomanage.userinfo(username,userpwd,hobby) values (#{username},#{userpwd},#{hobby});
    </insert>
    <!--修改-->
    <update id="updateUser" parameterType="com.yuan.pojo.User">
        update infomanage.userinfo set username=#{username},userpwd=#{userpwd},hobby=#{hobby} where id = #{id};
    </update>
    <!--删除-->
    <delete id="deleteUser" parameterType="int">
        delete from infomanage.userinfo where id = #{id};
    </delete>
```

### 3.测试类

```java
    @Test
    public void test(){
        //第一步 获取sqlsession对象
        SqlSession sqlSession = myBatisUtils.getSqlSession();
        //执行sql
        UserMapper sqlSessionMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = sqlSessionMapper.getUserList();
        for (User user:userList){
            System.out.print (user);
        }
//        关闭SqlSession
        sqlSession.close ();
    }
    @Test
    public void getUserById(){
        SqlSession sqlSession = myBatisUtils.getSqlSession();

        UserMapper sqlSessionMapper = sqlSession.getMapper(UserMapper.class);
        User user = sqlSessionMapper.getUserById(1);
        System.out.println(user);

        sqlSession.close();
    }
    @Test
    public void insertUser(){
        SqlSession sqlSession = myBatisUtils.getSqlSession();

        UserMapper sqlSessionMapper = sqlSession.getMapper(UserMapper.class);
        int x = sqlSessionMapper.insertUser(new User("gly","gly","1"));
        if(x>=0){
            System.out.print("success");
        }
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void updateUser(){
        SqlSession sqlSession = myBatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int x = mapper.updateUser(new User(21,"drgly","6654321","1"));
        if(x>=0){
            System.out.print("success");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteUser(){
        SqlSession sqlSession = myBatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int x = mapper.deleteUser(21);
        if(x>=0){
            System.out.print("success");
        }
        sqlSession.commit();
        sqlSession.close();
    }
```

### 4.Map

假设，我们的实体类，或者数据库中的表，字段或者参数过多，我们应当考虑map

### 5.类型别名

1. 别名为类名，首字母小写

```java
    <!--可以给实体类起别名-->
    <typeAliases>
        <package name="com.yuan.pojo"/>
    </typeAliases>
```

2.DIY一个类的别名，但是类太多时候会很烦

```java
  <!--可以给实体类起别名-->
    <typeAliases>
        <package name="com.yuan.pojo.User" alias="User"/>
    </typeAliases>
```

### 6.映射器

#### 使用resource

```java
    <!--每一个Mapper.xml都需要在mybatis核心配置中注册-->
    <mappers>
        <mapper resource="com/yuan/dao/UserMapper.xml"/>
    </mappers>
```



#### 使用class文件

```java
    <!--每一个Mapper.xml都需要在mybatis核心配置中注册-->
    <mappers>
        <mapper class="com.yuan.dao.UserMapper"/>
    </mappers>
```

#### 使用包

```java
    <!--每一个Mapper.xml都需要在mybatis核心配置中注册-->
    <mappers>
        <mapper name="com.yuan.dao"/>
    </mappers>
```

### 7.动态sql



## lomlok插件的使用

用处：在构造实体类的时候不需要再输入 重复的 get set 等等代码

### 使用步骤：

- idea中下载
- 项目中导入

```java
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.16</version>
    <scope>provided</scope>
</dependency>
```

- 加入注解

```xml
@ToString
@EqualsAndHashCode
@AllArgsConstructor, @RequiredArgsConstructor and @NoArgsConstructor
@Log, @Log4j, @Log4j2, @Slf4j, @XSlf4j, @CommonsLog, @JBossLog, @Flogger, @CustomLog
@Data
@Builder
@SuperBuilder
@Singular
@Delegate
@Value
@Accessors
@Wither
@With
@SneakyThrows
@val
@var
experimental @var
@UtilityClass
Lombok config system
```

```java
@Data
public class dept {
    private int deptid;
    private int deptcode;
    private int deptname;
    private int deptTime;
    private int deptstat;
}
```



## 二级缓存

要启用全局的二级缓存，只需要在你的 SQL 映射文件中添加一行：

```java
<cache/>
```

或者自定义参数

```xml
<cache
  eviction="FIFO"
  flushInterval="60000"
  size="512"
  readOnly="true"/>
```

效果：

```java
映射语句文件中的所有 select 语句的结果将会被缓存。
映射语句文件中的所有 insert、update 和 delete 语句会刷新缓存。
缓存会使用最近最少使用算法（LRU, Least Recently Used）算法来清除不需要的缓存。
缓存不会定时进行刷新（也就是说，没有刷新间隔）。
缓存会保存列表或对象（无论查询方法返回哪种）的 1024 个引用。
缓存会被视为读/写缓存，这意味着获取到的对象并不是共享的，可以安全地被调用者修改，而不干扰其他调用者或线程所做的潜在修改。
```

测试：

```java
        SqlSession sqlSession = myBatisUtils.getSqlSession();
//      新建 2 对象，测试是否从二级缓存读取
        SqlSession sqlSession2 = myBatisUtils.getSqlSession();
        UserMapper sqlSessionMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = sqlSessionMapper.getUserList();
        for (User user:userList){
            System.out.print (user);
        }
        sqlSession.close ();

//      2对象  测试是否从二级缓存读取
        UserMapper sqlSessionMapper2 = sqlSession2.getMapper(UserMapper.class);
        List<User> userList2 = sqlSessionMapper2.getUserList();
        for (User user:userList2){
            System.out.print (user);
        }
        sqlSession2.close ();
    }
```

结果

```xml
Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@2cc44ad]
//这里依旧在读取数据库
==>  Preparing: select * from infomanage.userinfo; 
==> Parameters: 
<==    Columns: id, username, userpwd, hobby
<==        Row: 1, yuanlei, yuanlei, 1
<==        Row: 2, stan, stan, 2
<==        Row: 3, still, still, 3
<==        Row: 4, admin, admin, 1
<==        Row: 21, sxy, sxy66666, 1
<==      Total: 5
User{id=1, username='yuanlei', userpwd='yuanlei', hobby='1}
User{id=2, username='stan', userpwd='stan', hobby='2}
User{id=3, username='still', userpwd='still', hobby='3}
User{id=4, username='admin', userpwd='admin', hobby='1}
User{id=21, username='sxy', userpwd='sxy66666', hobby='1}
Resetting autocommit to true on JDBC Connection [com.mysql.jdbc.JDBC4Connection@2cc44ad]
Closing JDBC Connection [com.mysql.jdbc.JDBC4Connection@2cc44ad]
//储存数据到二级缓存中
Returned connection 46941357 to pool.
//获取二级缓存
Cache Hit Ratio [com.yuan.dao.UserMapper]: 0.5
User{id=1, username='yuanlei', userpwd='yuanlei', hobby='1}
User{id=2, username='stan', userpwd='stan', hobby='2}
User{id=3, username='still', userpwd='still', hobby='3}
User{id=4, username='admin', userpwd='admin', hobby='1}
User{id=21, username='sxy', userpwd='sxy66666', hobby='1}
Disconnected from the target VM, address: '127.0.0.1:4985', transport: 'socket'

```







## 日志的控制台输出

### logImpl

1. SLF4J 
2. LOG4J  【掌握】
3. LOG4J2 
4.  JDK_LOGGING 
5.  COMMONS_LOGGING 
6.  STDOUT_LOGGING  【掌握】
7. NO_LOGGING

标准日志输出 STDOUT_LOGGING  

在核心配置文件 mybatis-config.xml中配置

##### 标准日志输出

```java
    <!--引入日志-->
    <settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>
```

##### LOG4J日志输出

现在pom.xml里面导入包

```xml
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
```



然后再xml里面输入

```java
    <!--引入日志-->
    <settings>
        <setting name="logImpl" value="LOG4J"/>
    </settings>
```



# IDEA 快捷键常用

**Ctrl+Shift+A**  快捷指令栏

**ALT+ENTER** 快速填充

**ctrl+/** 快速注释

**ALT+INSERT** GENERATE

# [Spring 开源下载](https://repo.spring.io/release/org/springframework/spring/)

参考视频点击[此处](https://www.bilibili.com/video/BV1WE411d7Dv?from=search&seid=6506222528246419724)









