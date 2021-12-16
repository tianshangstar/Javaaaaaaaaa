package com.evan.javaaaaaaaaa.algorithm.leetcode.q_300;

import java.util.Arrays;

public class Solution {

    // 假设
    // 1. 用dp[i-1]表示以nums[i-1]结尾的最长子数组的长度
    // 则有 dp(n) = max(dp[n], dp[m] + 1) m ∈ [0, n)
    // 这里做dp分析容易思路走错了，千万要记得dp[n]表示的是以元素nums[n]结尾的最长递增子序列的长度
    public int lengthOfLIS(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 记录长度的数组初始化，以每个元素结尾的最长递增子序列最短是1
        int[] lengthArr = new int[nums.length];
        Arrays.fill(lengthArr, 1);

        int lengthOfLIS = 1;

        // n = 0，则序列肯定是1，所以循环从1开始
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && lengthArr[j] + 1 > lengthArr[i]) {
                    lengthArr[i] = lengthArr[j] + 1;
                }
            }
            lengthOfLIS = Math.max(lengthOfLIS, lengthArr[i]);
        }

        return lengthOfLIS;
    }

    /**
     * 二分法的思路
     * 依旧用一个辅助数组，但是辅助数组保存的并不是以原数组下标为结尾的最长递增子序列
     * 辅助数组保存的是，递增子序列长度为i时，子序列最后一个元素的最小值的在原数组的下标
     */
    public int lengthOfLIS1(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 数组保存的是长度为n的时候，子序列的末尾元素的最小值。
        int[] lengthArr = new int[nums.length + 1];

        int lengthOfLIS = 1;
        lengthArr[lengthOfLIS] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > lengthArr[lengthOfLIS]) {
                // 长度增长
                lengthArr[++lengthOfLIS] = nums[i];
            } else {
                // 遍历 1-lengthOfLIS,看看nums[i]是否可以作为某一个长度的子序列的最后一个元素
                int l = 1, r = lengthOfLIS;
                // 用来记录比nums[i]小的 最大的子序列末尾元素
                int pos = 0;
                int m;
                // 利用二分法找到需要更新子序列末尾元素的下标
                while (l <= r) {
                    m = (l + r) / 2;
                    if (lengthArr[m] < nums[i]) {
                        // 更新目标索引
                        pos = m;
                        // 更适合的目标索引只会出现在右边
                        l = m + 1;
                    } else {
                        // 更适合的目标索引只会出现在左边
                        r = m - 1;
                    }
                }
                // pos的对应的下一个下表就是要更新的元素
                lengthArr[pos + 1] = nums[i];
            }
        }

        return lengthOfLIS;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12};

        Solution solution = new Solution();
        System.err.println(solution.lengthOfLIS(arr));
        System.err.println(solution.lengthOfLIS1(arr));
    }
}
