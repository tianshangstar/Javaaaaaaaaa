package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_268;

class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < nums.length; i++) {
            // a ^ a = 0; a ^ 0 = a;这里相当于用n过滤一遍。
            n ^= i ^ nums[i];
        }

        return n;
    }
}