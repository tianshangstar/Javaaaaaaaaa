package com.evan.javaaaaaaaaa.jvm.dynamic_type;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;

public class FunctionDispatch {

    // 默认构造函数的作用域取决于类定义的作用域
    public class GrandFather {
        void thinking() {
            System.out.println("I am grandfather");
        }
    }

    class Father extends GrandFather {
        @Override
        void thinking() {
            System.out.println("I am father");
        }
    }

    class Son extends Father {
        @Override
        void thinking() {
            //怎么调用GrandFather的thinking()? 不用invoke包下的api
            //方法1，简单粗暴
            new GrandFather().thinking();
            //方法2，用invoke包下的api
            //一些有意思的事情： 注意，非static内部类，默认构造函数会隐藏一个外部类的引用。 so......
            //下面还有一个static内部类的例子
//            MethodType constructorMethodType = MethodType.methodType(void.class, FunctionDispatch.class);
//            MethodType methodType = MethodType.methodType(void.class);

            try {
//                MethodHandle constructorHandle = MethodHandles.lookup().findConstructor(GrandFather.class, constructorMethodType);
//
//                GrandFather o = (GrandFather) constructorHandle.invokeExact(new FunctionDispatch());
//
//                MethodHandle funHandle = MethodHandles.lookup().findVirtual(GrandFather.class, "thinking", methodType).bindTo(o);
//
//                funHandle.invoke();

                MethodType mt = MethodType.methodType(void.class);
                MethodHandle methodHandle = MethodHandles.lookup().findSpecial(GrandFather.class, "thinking", mt, getClass());
                methodHandle.invoke(this);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    static class StaticClass{
        void print(){
            System.out.println("static class");
        }
    }



    public static void main(String[] args) {
        FunctionDispatch fd = new FunctionDispatch();

        Son son = fd.new Son();
        son.thinking();

        MethodType staticMethodType = MethodType.methodType(void.class);

        try {
            // static内部类的构造函数，不需要外部类的引用，so.....
            MethodHandle staticHandle = MethodHandles.lookup().findConstructor(StaticClass.class, staticMethodType);
            StaticClass sc = (StaticClass) staticHandle.invokeExact();
            sc.print();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        // 那么反射还需要这个外部类的引用吗，试一下
//        Constructor[] constructors = GrandFather.class.getConstructors();
        try {
            // 事实证明，非static内部类，用反射的方式获取constructor并且创建都想，都需要外部类的引用。
            // getDeclaredConstructor 获取一切符合参数列表的构造函数
            Father f = Father.class.getDeclaredConstructor(FunctionDispatch.class).newInstance(new FunctionDispatch());
            f.thinking();
            // getConstructor 只能获取public的构造函数
            GrandFather gf = GrandFather.class.getConstructor(FunctionDispatch.class).newInstance(new FunctionDispatch());
            gf.thinking();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
