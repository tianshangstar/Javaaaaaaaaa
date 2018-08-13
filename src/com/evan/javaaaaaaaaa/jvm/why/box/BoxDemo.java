package com.evan.javaaaaaaaaa.jvm.why.box;

public class BoxDemo {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 128;
        Integer f = 128;
        Long g = 3L, h=3L, i = 128L, j = 128L;


        System.out.println(c == d); // true ?????? IntegerCache -128~127,进行的是原生数据类型的比较
        System.out.println(e == f); // false ?????? 不是从IntegerCache中取，so..
        System.out.println(c == (a + b)); // true ==运算符在遇到算数运算会自动拆箱
        System.out.println(c.equals(a + b)); // true a + b自动封箱
        System.out.println(g == (a + b)); // true ==运算符在遇到算数运算会自动拆箱
        System.out.println(g.equals(a + b)); // 一直是false，因为g是long a+b不是long
        System.out.println(g == h);
        System.out.println(i == j);
    }
}
