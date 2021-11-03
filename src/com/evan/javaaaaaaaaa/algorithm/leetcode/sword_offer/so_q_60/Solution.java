package com.evan.javaaaaaaaaa.algorithm.leetcode.sword_offer.so_q_60;

public class Solution {

    /**
     * 解法思路是这样：
     * 假设有一堆骰子 n个，
     * 那么当计算可能投掷的点数时，可以把骰子看成两堆，一堆是1个，另一堆是n-1个
     * 递归下去，就变成了枚举每一个骰子可能出现的点数(1-6)
     */
    public double[] dicesProbability(int n) {

        // 骰子数非法
        if (n < 1) {
            return new double[0];
        }

        // 骰子个数  最大值  可能出现的数值个数
        // 1        6       1~6  6个
        // 2        12      2~12 11个
        // 3        18      3~18 16个
        // ...
        // n        6 * n   6*n-n + 1
        // 有n个骰子，可能投出的数在 n = 6 ^ n之间
        int minValue = n;

        int maxValue = 6 * n;

        // 定义一个数组，存放每一种可能投掷出的数字的出现次数
        int count = maxValue - minValue + 1;
        int[] probabilities = new int[count];

        // 这样定义，期望值是x的情况下，可能投掷出x的次数保存在x-n的位置上
        // 初始化
        for (int i = 0; i < count; i++) {
            probabilities[i] = 0;
        }

        // 计算每种点数出现的概率
        dicesProbability(n, probabilities);

        // 可能投掷出的组合
        // 骰子个数 可能出现的组合
        //  1       1~6     6个组合
        //  2       2~12    36个组合
        // ...
        //  n       n~6*n   6^n个组合
        double totalScene = Math.pow(6, n);

        double[] ratios = new double[maxValue - minValue + 1];

        for (int i = 0; i < count; i++) {
            ratios[i] = probabilities[i] / totalScene;
        }

        return ratios;
    }

    /**
     * 穷举所有的可能出现的点数，并计算可能出现的场景，同时更新数组的计数
     *
     * @param n             需要穷举的骰子个数
     * @param probabilities 保存每个值出现次数的数据
     */
    private void dicesProbability(int n, int[] probabilities) {
        // 递归入口，这里可以理解成先投掷第一个骰子，并取值
        for (int i = 1; i <= 6; i++) {
            dicesProbability(n, n, i, probabilities);
        }
    }


    /**
     * 分堆统计
     *
     * @param original      总骰子个数
     * @param current       当前骰子个数
     * @param currentSum    当前投掷出的点数和
     * @param probabilities 保存每个值出现次数的数据
     */
    private void dicesProbability(int original, int current, int currentSum, int[] probabilities) {
        if (current == 1) {
            // 数组下标0表示的是最小的可能投掷出的点数，即 original， currentNum表示当前投掷出的点数和
            // 所以，当投掷到最后一个骰子的时候，需要将对应 currentSum-original的数组位置的值 ++
            probabilities[currentSum - original]++;
        } else {
            // 这里就是递归的核心，永远尝试把骰子分成两堆，一堆是1个，另一堆是n-1个
            for (int i = 1; i <= 6; i++) { // 递归
                dicesProbability(original, current - 1, i + currentSum, probabilities);
            }
        }
    }

}
