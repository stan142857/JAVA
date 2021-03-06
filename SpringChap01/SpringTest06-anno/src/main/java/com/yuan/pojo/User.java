package com.yuan.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component 组件 等价于  applicationContext.xml 中<bean id="user" class="com.yuan.pojo.User"/>
@Component
@Scope("singleton")
public class User {
    //测试注解开发  简单的可以，复杂的还是走配置文件
    //相当于 <property name="age" value="26">
    @Value("27")
    public String age;

    //测试注解开发 set方法  优先使用 set ，后面使用定义时的对象
    @Value("26")
    public void setAge(String age) {
        this.age = age;
    }

    //对比注解开发  赋值模式
    public String name = "stan 注解开发";
}
