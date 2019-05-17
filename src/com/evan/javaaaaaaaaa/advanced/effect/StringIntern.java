package com.evan.javaaaaaaaaa.advanced.effect;

/**
 * Created by evan01.zhang on 2018/9/19.
 * 对于什么时候会在常量池存储字符串对象，我想我们可以基本得出结论:
 * 1. 显示调用String的intern方法的时候;
 * 2. 直接声明字符串字面常量的时候，例如: String a = "aaa";
 * 3. 字符串直接常量相加的时候，例如: String c = "aa" + "bb";
 * 其中的aa/bb只要有任何一个不是字符串字面常量形式，都不会在常量池生成"aabb".
 * 且此时jvm做了优化，不会同时生成"aa"和"bb"在字符串常量池中
 * --------------结论不是我总结的。
 */
public class StringIntern {
    public static void main(String[] args) {
        String s1 = new String("aaa");
        String s2 = "aaa";
        System.out.println("1 : " + (s1 == s2)); // 问题1

        String s3 = new String("bbb").intern();
        String s4 = "bbb";
        System.out.println("2 : " + (s3 == s4)); // 问题2

        String s5 = "ccc";
        String s6 = "ccc";
        System.out.println("3 : " + (s5 == s6)); // 问题3

        String s7 = new String("ddd").intern();
        String s8 = new String("ddd").intern();
        System.out.println("4 : " + (s7 == s8)); // 问题4

        String s9 = new String("eee");
        String s10 = new String("eee");
        System.out.println("5 : " + (s9 == s10)); // 问题5

        String s11 = "abcd";
        String s12 = "abcd";
        System.out.println("6 : " + (s11 == s12)); // 问题6

        String temp = "hh";
        String s13 = "a" + temp;
        String s14 = "ahh";
        System.out.println("7 : " + (s13 == s14)); // 问题7

        String s15 = new String("11");
        s15.intern();
        String s16 = "11";
        System.out.println("8 : " + (s15 == s16)); // 问题8

        String s17 = new String("2") + new String("2");
        s17.intern(); // jdk1.7之后，常量池不仅仅可以存储对象，还可以存储对象的引用，会直接将s17的地址存储在常量池
        String s18 = "22"; // jdk1.7之后，常量池中的地址其实就是s3的地址
        // jdk1.7之前false， jdk1.7之后true
        System.out.println("9 : " + (s17 == s18)); // 问题9
    }
}
