package com.evan.javaaaaaaaaa.advanced.effect.clone;

import java.util.Arrays;

/**
 * Created by evan01.zhang on 2018/8/23.
 */
public class Foo {

    MyInt[] myInts = {new MyInt(1), new MyInt(2), new MyInt(3)};

    public Foo() {
    }

    public Foo(Foo src) {
        for (int i=0; i<src.myInts.length; i++) {
            this.myInts[i] = new MyInt(src.myInts[i].i);
        }
    }

    public static Foo deepCopy(Foo src) {
        Foo dest = new Foo();
        for (int i=0; i<src.myInts.length; i++) {
            dest.myInts[i] = new MyInt(src.myInts[i].i);
        }
        return dest;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "myInts=" + Arrays.toString(myInts) +
                '}';
    }

    public static void main(String[] args) {
        Foo src = new Foo();
        Foo constructor_clone = new Foo(src);
        Foo factory_clone = Foo.deepCopy(src);
        src.myInts[1].i = 99;
        System.out.println(src);
        System.out.println(constructor_clone);
        System.out.println(factory_clone);
    }
}
