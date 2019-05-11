package com.evan.javaaaaaaaaa.jvm.why.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果这里编译期拒绝编译，那么证明返回值不同在字节码文件中对于方法的特征签名没有影响
 * <p>
 * 原则上，在class文件格式中，只要描述符不同的两个方法就可以并存。
 * 但是返回值并不包含在特征签名当中，这部分就有编译器不同而决定了
 */
public class GenOverload1 {

//    public static String method(List<String> list) {
//        return "";
//    }

    public static int method(List<Integer> list) {
        return 1;
    }

    public static void main(String[] args) {
//        method(new ArrayList<String>());
        method(new ArrayList<Integer>());
    }
}
