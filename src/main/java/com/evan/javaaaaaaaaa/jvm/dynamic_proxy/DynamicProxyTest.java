package com.evan.javaaaaaaaaa.jvm.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {

    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello{
        @Override
        public void sayHello() {
            System.out.println("hello, world!");
        }
    }

    static class DynamicProxy implements InvocationHandler{

        Object originalObj;

        Object bind(Object originalObj){
            this.originalObj = originalObj;
            // 核心在这里，Proxy.newProxyInstance返回了一个实现了IHello的接口，并且代理了new Hello()实例行为的队形
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(), originalObj.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(originalObj, args);
        }
    }

    public static void main(String[] args) {
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        hello.sayHello();
    }

}
