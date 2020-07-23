package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_461;

/**
 * 拆分两部分
 * 1、将俩数字不同的位标识出来
 * 2、数不同的位的个数
 */
public class Solution {
    public int hammingDistance(int x, int y) {
        // 不同的位为1
        int t = x ^ y;
        // 数1
        int count = 0;
        while (t != 0) {
            t &= (t - 1);
            count++;
        }
        return count;
    }
}
