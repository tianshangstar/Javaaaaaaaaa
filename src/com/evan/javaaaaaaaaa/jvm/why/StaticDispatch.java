package com.evan.javaaaaaaaaa.jvm.why;

/**
 * static dispatch
 * <p>
 * 核心：静态类型， 静态类型是在编译期可知的，并不会在运行时进行最终对象类型判定
 * <p>
 * 虚拟机（准确的说是编译期）在执行重载时，使用的是参数的静态类型
 */
public class StaticDispatch {
    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello, guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello, gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello, lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch staticDispatch = new StaticDispatch();
        staticDispatch.sayHello(man);
        staticDispatch.sayHello(woman);
    }
}
