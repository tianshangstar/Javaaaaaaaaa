package com.evan.javaaaaaaaaa.advanced.effect.clone;

import java.util.Arrays;

/**
 * Created by evan01.zhang on 2018/8/23.
 */
public class ArraysClone implements Cloneable {
    MyInt[] myInts = {new MyInt(1), new MyInt(2), new MyInt(3)};

    @Override
    protected ArraysClone clone() throws CloneNotSupportedException {
        return (ArraysClone) super.clone();
    }

    @Override
    public String toString() {
        return "ArraysClone{" +
                "myInts=" + Arrays.toString(myInts) +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        ArraysClone ac = new ArraysClone();
        ArraysClone ac_clone = ac.clone();
        ac.myInts[1].i = 5;
        System.out.println(ac);
        System.out.println(ac_clone);
    }
}
