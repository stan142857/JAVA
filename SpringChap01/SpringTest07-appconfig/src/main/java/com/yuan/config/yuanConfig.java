package com.yuan.config;

import com.yuan.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

//@Configuration  这个也会被Spring容器托管，注册到容器中，因为它本身就是一个@Component，即组件
//@Configuration  代表这是一个配置类，就和我们之前看的beans.xml医院
@Configuration
//spring 可以完全摈弃配置  新特性  使用如下
//@ComponentScan("com.yuan.pojo")
//@Import(yuanConfig.class)
public class yuanConfig {


    //注册一个bean，就相当于我们之前写的一个bean标签
    //这个方法的名字，就相当于bean标签中的id属性
    //这个方法的返回值，就相当于bean标签中的class属性
    @Bean
    public User getUser(){
        return new User();//就是返回要注入的bean对象
    }
}
