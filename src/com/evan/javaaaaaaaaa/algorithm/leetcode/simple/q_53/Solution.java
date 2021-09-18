package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_53;

/**
 * 这题只是看似简单，实际上理解的依旧很操蛋。
 */
public class Solution {

    /**
     * 基于动态规划
     * <p>
     * 设：数组nums长度为n，0 <= i <= n-1
     * 用函数f(i)表示已i为结尾的子序列的最大和
     * 则f(0) = arr[0]
     * <p>
     * 类似的，可以把arr[i]理解成一个子序列的最大值，f(i-1)也理解成一个子序列的最大值。
     * 那么此时有这样一种描述：
     * 那么此时的f(i)=max(f(i-1) + nums[i], nums[i])
     *
     * 为什么表达式描述成上诉这样？
     * 因为f(i)描述的是以下标为i结尾的自学列的最大sum，也就是说这个sum总一定包含sum[i]
     *
     * 可以{-2, 1, -3, 4, -1, 2, 1, -5, 4}为例
     */
    public int maxSubArray(int[] nums) {

        int maxSubSum = nums[0];
        // 这里我本来初始化成Integer.MIN_VALUE，但是事实证明如果遇到第一个元素是负数会负负得正。。。
        int maxPreSubSum = 0;

        for (int num : nums) {
            // 因为f(i)描述的是以nums[i]为结尾的子序列的最大值，nums[i]一定要参与进去。
            maxPreSubSum = Math.max(maxPreSubSum + num, num);
            maxSubSum = Math.max(maxPreSubSum, maxSubSum);
        }
        return maxSubSum;
    }

    public static void main(String[] args) {

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums1 = {1};

        Solution solution = new Solution();
        System.err.println(solution.maxSubArray(nums));
        System.err.println(solution.maxSubArray(nums1));
    }
}
