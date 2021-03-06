package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_26;

public class Solution {

    public int removeDuplicates(int[] nums) {

        if (nums == null) {
            return -1;
        }

        int size = nums.length;

        if (size == 0 || size == 1) {
            return size;
        }

        int h = 1;
        int t = 1;
        int v = nums[0];
        // 如果初始化 h = t = 0的话，就不需要v了（因为这样v一直等于nums[h]）
        while (t < size) {
            if (v != nums[t]) {
                v = nums[h] = nums[t];
                h++;
            }
            t++;
        }

        return h;
    }

}
