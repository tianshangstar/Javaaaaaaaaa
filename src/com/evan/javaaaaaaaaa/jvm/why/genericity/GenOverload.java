package com.evan.javaaaaaaaaa.jvm.why.genericity;

import java.util.List;

public class GenOverload {
    // java泛型的实现是采用的类型擦除，所以这里无法编译通过。
    // 类型擦除后两个方法的定义一致。
//    public void method(List<String> list) {
//
//    }

    public void method(List<Integer> list) {

    }
}
