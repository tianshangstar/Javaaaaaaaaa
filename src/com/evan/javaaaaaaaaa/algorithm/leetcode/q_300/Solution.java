package com.evan.javaaaaaaaaa.algorithm.leetcode.q_300;

import java.util.Arrays;

public class Solution {
    public int lengthOfLIS(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 假设
        // 1. 用dp[i-1]表示以nums[i-1]结尾的最长子数组的长度
        // 则有 dp(n) = max(dp[n], dp[m] + 1) m ∈ [0, n)
        // 这里做dp分析容易思路走错了，千万要记得dp[n]表示的是以元素nums[n]结尾的最长递增子序列的长度

        // 记录长度的数组初始化，以每个元素结尾的最长递增子序列最短是1
        int[] lenthArr = new int[nums.length];
        Arrays.fill(lenthArr, 1);

        int lengthOfLIS = 1;

        // n = 0，则序列肯定是1，所以循环从1开始
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && lenthArr[j] + 1 > lenthArr[i]) {
                    lenthArr[i] = lenthArr[j] + 1;
                }
            }
            lengthOfLIS = Math.max(lengthOfLIS, lenthArr[i]);
        }

        return lengthOfLIS;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12};

        Solution solution = new Solution();
        System.err.println(solution.lengthOfLIS(arr));
    }
}
