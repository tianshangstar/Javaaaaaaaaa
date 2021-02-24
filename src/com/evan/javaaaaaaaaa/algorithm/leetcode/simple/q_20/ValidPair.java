package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_20;

public class ValidPair {

    public static void main(String[] args) {
        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "[{()}]";


        Solution solution = new Solution();
        System.err.println("s1 : " + solution.isValid(s1));
        System.err.println("s2 : " + solution.isValid(s2));
        System.err.println("s3 : " + solution.isValid(s3));
    }
}
