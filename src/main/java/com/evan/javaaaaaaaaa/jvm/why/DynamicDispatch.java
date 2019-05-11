package com.evan.javaaaaaaaaa.jvm.why;

/**
 * 区别于static dispatch
 * dynamic dispatch 方法重写是在运行时确定最终需要执行的方法
 * jvm在执行方法的时，是由栈顶的实现类优先查找是否有对应符合方法标志的方法
 * 若没有，向父类查找。
 *
 * -------
 * override
 */
public class DynamicDispatch {
    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say Hello");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("woman say Hello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
}
