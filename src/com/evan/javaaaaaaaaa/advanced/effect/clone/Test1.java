package com.evan.javaaaaaaaaa.advanced.effect.clone;

import java.util.Arrays;

/**
 * Created by evan01.zhang on 2018/8/23.
 */
public class Test1 {
    public static void main(String[] args) {
        String[] strs = {"aa", "bb", "cc"};
        String[] strs_clone = strs.clone();
        strs[1] = "dd";
        System.out.println(Arrays.toString(strs));
        System.out.println(Arrays.toString(strs_clone));
    }
}
