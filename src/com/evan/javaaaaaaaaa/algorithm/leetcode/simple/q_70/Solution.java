package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_70;

public class Solution {

    // 别管会不会了，先做出来吧！！！！！！
    public int climbStairs(int n) {
        if (n == 0 || n == 1 || n == 2)
            return n;

        // 数组下标表示要走的步长
        int[] solutions = new int[n + 1];
        // n-1 -> n 只有1种走法 定义为A（1）
        // n-2 -> n 有2中走法，11, 2 定义为B（2）
        // n-3 -> n 有几种走法呢？ 111，12，21 有3种，定义为C（3）
        // 可以理解为111、12的走法为先走到B的场景，21的走法为先走到c的场景
        // 所以C = A + B
        // n-4 -> n ，1111,121,112,211,22 定义为D = C + B
        // 所以，我的第一版这么玩：
        solutions[0] = 0;
        solutions[1] = 1;
        solutions[2] = 2;

        //solutions[n] = solutions[n-1] + solutions[n-2];
        for (int i = 3; i <= n; i++) {
            solutions[i] = solutions[i - 1] + solutions[i - 2];
        }

        return solutions[n];
    }
}
