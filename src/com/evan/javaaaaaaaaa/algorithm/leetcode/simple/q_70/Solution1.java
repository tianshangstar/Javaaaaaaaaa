package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_70;

public class Solution1 {

    // 优化版，有必要记录所有的步长走法吗？没有啊
    public int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }

        // l表示上一个，ll表示上上一个，s表示返回值
        int l = 0, ll = 0, s = 1;

        for (int i = 1; i <= n; i++) {
            // 前进
            ll = l;
            l = s;
            // 这是循环里面写的第一行，solution = last + last.last
            s = l + ll;
        }

        return s;
    }

}
