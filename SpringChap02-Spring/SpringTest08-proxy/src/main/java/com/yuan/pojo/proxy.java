package com.yuan.pojo;

//中介
public class proxy implements Rent{
    private Host host;
    public proxy(){

    }
    public proxy(Host host){
        this.host = host;
    }

    public void rent(){
        //代理帮房东租房子
        host.rent();
    }

    //看房
    public void seeHouse(){
        System.out.println("中介带你看房");
    }

    //收中介费
    public void sign(){
        System.out.println("中介签租房合同");
    }

    //收中介费
    public void fare(){
        System.out.println("中介收中介费");
    }
}
