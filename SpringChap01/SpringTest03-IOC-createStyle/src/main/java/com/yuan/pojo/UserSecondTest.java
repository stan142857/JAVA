package com.yuan.pojo;

public class UserSecondTest {
    private String testName;

    public UserSecondTest(String testName) {
        this.testName = testName;
        System.out.println(testName+": testName被创建");
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void show(){
        System.out.println("testName"+testName);
    }
}
