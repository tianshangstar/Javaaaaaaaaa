package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_344;

public class Solution {
    public void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }

        // 双指针
        int h = 0, t = s.length - 1;

        char a;
        while (h <= t) {
            a = s[h];
            s[h++] = s[t];
            s[t--] = a;
        }
    }
}
