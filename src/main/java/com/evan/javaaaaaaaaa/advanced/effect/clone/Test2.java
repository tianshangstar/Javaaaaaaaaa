package com.evan.javaaaaaaaaa.advanced.effect.clone;

import java.util.Arrays;

/**
 * Created by evan01.zhang on 2018/8/23.
 */
public class Test2 {
    public static void main(String[] args) {
        MyInt[] myInts = {new MyInt(1), new MyInt(2), new MyInt(3)};
        MyInt[] myInts_clone = myInts.clone();
        myInts[1].i = 5;
        System.out.println(Arrays.toString(myInts));
        System.out.println(Arrays.toString(myInts_clone));
    }
}
