package com.evan.javaaaaaaaaa.algorithm.leetcode.sword_offer.so_q_60;

import java.util.Arrays;

public class Solution1 {

    /**
     * leetcode上面Krahets的思路
     * <p>
     * 假设已经知道n-1个骰子的解 f(n-1)
     * 那么我们假设此时求n个骰子的解f(n)
     * 用x来表示n-1个骰子可能投掷出的点塑，则n个骰子可能投掷出的点数分辨是x+1 x+2 ... x+6
     * <p>
     * 如果求n个骰子点数为k的概率 f(n,k)
     * 由于单个骰子可以投出1~6， 所以可以简单的推理出 f(n, k) = f(n-1, k-1) + f(n-1, k-2) + f(n-1, k-3) ... + f(n-1, k-6)
     * <p>
     * 已知：
     * f(1)
     * 则求解f(2, 1)按照上诉公式
     * f(2, 2) = f(1, 2-1) + f(1, 2-2) + f(1, 2-3) ... 会有越界问题
     * -----------------------------------------------------------
     * 由于反向推倒有越界问题，所以换正向推倒。
     * f(1) : f(1, 1) = 1/6  f(1, 2) = 1/6  f(1, 4) = 1/6  f(1, 4) = 1/6  f(1, 5) = 1/6  f(1, 6) = 1/6
     * <p>
     * <p>
     * f(2) :
     * 第一个骰子1点*第二个骰子1点
     * f(2, 2) = f(2-1, 1) * f(2-2, 1) = 1/6 * 1/6 = 1/36
     * f(2, 3) = f(2-1, 1) * f(2-2, 2) + f(2-1, 2) + f(2-2, 1) = 1/6 * 1/6 + 1/6 * 1/6 = 2/36
     * f(2, 4) = f(2-1, 1) * f(2-2, 3) + f(2-1, 2) * f(2-2, 2) + f(2-1, 3) + f(2-2, 1) = 3/36
     * ....
     * 可以推倒出，对于n个骰子
     * n <= m <= 6*n
     * f(n) = f(n-1, m-1), f(n-1, m-2), f(n-1, m-3), ...  f(n-1, m-6)
     *
     * @param n
     * @return
     */
    public double[] dicesProbability(int n) {
        if (n <= 0) {
            return new double[0];
        }

        double[] arr = new double[6 * n];
        Arrays.fill(arr, 0);
        // 第一个骰子
        for (int i = 0; i < 6; i++) {
            arr[i] = 1d / 6;
        }

        if (n == 1) {
            return arr;
        }

        // 初始化数组已经初始化好了1个骰子的场景，所以后面可以直接从2开始
        for (int i = 2; i <= n; i++) {
            // 新建一个数组，保存增加一个骰子后每个点数出现的概率
            double[] temp = new double[i * 5 + 1];
            // 新增一个骰子，可以投掷的点数范围是 i (骰子个数) - i * 6
            for (int k = 0; k < arr.length; k++) {
                for (int m = 0; m < 6; m++) {
                    temp[m + k] += arr[k] / 6;
                }
            }

            // 交换保存投掷结果
            arr = temp;
        }

        return arr;
    }
}
