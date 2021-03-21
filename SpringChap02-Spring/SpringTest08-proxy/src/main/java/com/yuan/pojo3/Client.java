package com.yuan.pojo3;

public class Client {
    public static void main(String[] args) {
        //真实角色
        Host host = new Host();

        //代理角色：现在没有
        ProxyInvocationHander hander = new ProxyInvocationHander();
        //通过调用程序处理角色  来处理我们要调用的接口对象
        hander.setRent(host);
        Rent proxy = (Rent) hander.getProxy();//这里的proxy就是动态生成的，我们并没有写代码
        proxy.rent();

    }
}
