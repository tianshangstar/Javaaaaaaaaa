package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_344;

import com.alibaba.fastjson.JSON;

public class ReverseString {

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[] str = "abc".toCharArray();
        char[] str1 = "a".toCharArray();
        char[] str2 = "".toCharArray();
        char[] str3 = "hello".toCharArray();

        solution.reverseString(str);
        solution.reverseString(str1);
        solution.reverseString(str2);
        solution.reverseString(str3);
        System.err.println(JSON.toJSONString(str));
        System.err.println(JSON.toJSONString(str1));
        System.err.println(JSON.toJSONString(str2));
        System.err.println(JSON.toJSONString(str3));
    }

}
