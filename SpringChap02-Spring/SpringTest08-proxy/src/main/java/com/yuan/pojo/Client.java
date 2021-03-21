package com.yuan.pojo;

//租房者 我
public class Client {
    public static void main(String []args){
        //房东要租房子
        Host host = new Host();
        //直接找房东租房子  但是真实情况下一般找不到 只能找中介
        host.rent();
        //代理，中介帮房东租房子，但代理角色一般会有一些附属操作，比如中介收钱
        proxy proxy = new proxy(host);
        //你不用面对房东，直接找中介租房即可
        proxy.rent();
    }
}
