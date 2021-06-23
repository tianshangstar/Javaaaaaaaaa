package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_121;

public class Solution {

    // 暴力强解
    // 如果数组长度很长的话，暴力强解太浪费时间了
    public int maxProfit(int[] prices) {

        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int length = prices.length;

        int maxProfit = 0;

        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {

                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }

        return maxProfit;
    }

    /**
     * 假设：
     * 在第一天买入了股票；
     * 如果第二天价格高于第一天，那么第二天卖出可以得出一个收益
     * 如果第二天的价格低于第一天，那么则第二天买入肯定可以获得更高的收益（如果有的情况下）
     * ....
     * 将第一天和第二天分别替换成第n天和第n+1天，则得到了时间复杂度O(1)、空间复杂度O(1)的解法
     */
    public int maxProfit1(int[] prices) {

        int minIndex = 0;
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {

            if (prices[i] < prices[minIndex]) {
                minIndex = i;
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - prices[minIndex]);
            }
        }

        return maxProfit;
    }
}
