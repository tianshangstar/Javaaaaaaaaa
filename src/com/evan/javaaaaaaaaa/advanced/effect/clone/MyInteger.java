package com.evan.javaaaaaaaaa.advanced.effect.clone;

/**
 * Created by evan01.zhang on 2018/8/23.
 */
public class MyInteger implements Cloneable {

    MyInt myInt = new MyInt(0);

    public MyInteger(int i) {
        myInt = new MyInt(i);
    }

    @Override
    protected MyInteger clone() throws CloneNotSupportedException {
        MyInteger clone = (MyInteger) super.clone();
        clone.myInt = new MyInt(this.myInt.i);
        return clone;
    }

    @Override
    public String toString() {
        return "MyInteger{" +
                "myInt=" + myInt +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        MyInteger myInteger = new MyInteger(1);
        MyInteger myInteger1_clone = myInteger.clone();

        myInteger.myInt.i = 15;
        System.out.println(myInteger);
        System.out.println(myInteger1_clone);
    }
}
