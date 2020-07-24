package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_204;

import java.util.Arrays;

/**
 * 学习一下思路，上一个算法准确性上没问题，效率实在是太慢了
 * 依旧是排除法
 * 但是换一种实现方式，不需要全部遍历的
 */
public class Solution1 {

    public int countPrimes(int n) {
        //其实可以用BitSet来解决的，但是还是自己实现吧
        char[] nums = new char[n];
        Arrays.fill(nums, '1');

        // 之前我的思路是n/2，但是看过其他人的思路后，其实sqrt(n)就足够了
        // 但是0或者1怎么去掉？
        for (int i = 2; i * i < n; i++) {
            if (nums[i] == '1') {
                // 步长，步长啊，不用+1，直接+i才是最优解!!!!
                for (int j = i * i; j < n; j += i) {
                    nums[j] = '0';
                }
            }
        }

        // count 1，1表示质数。
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i] == '1')
                count++;
        }

        System.err.println("n=" + n);
        System.err.println("count=" + count);
        System.err.println("++++++++++++++++++++++++++++++++");
        return count;
    }
}
