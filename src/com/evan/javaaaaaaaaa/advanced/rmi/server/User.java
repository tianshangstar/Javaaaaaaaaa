package com.evan.javaaaaaaaaa.advanced.rmi.server;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -2019585366981867310L;

    private String userName;
    private int age;
    private String sex;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
