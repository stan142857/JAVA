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



# 日志的控制台输出

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













