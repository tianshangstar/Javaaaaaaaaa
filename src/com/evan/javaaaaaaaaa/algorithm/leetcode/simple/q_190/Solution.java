package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_190;

/**
 * 被题目最后一句扎心了：重复调用咋整？
 * ------------------------
 * 想俩小时之后（其实是知乎开爽文）
 * <p>
 * 上表
 */
public class Solution {

    public int reverseBits(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            result = result << 1;
            result = n & 1 | result;
            n = n >> 1;
        }

        return result;
    }
}
