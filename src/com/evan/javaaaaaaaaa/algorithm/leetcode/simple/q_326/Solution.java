package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_326;

class Solution {
    // 循环
    public boolean isPowerOfThree(int n) {

        if (n < 1) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }

    /**
     * 官方叫基准替换，我觉得叫进制替换比较好
     * 比如：
     * 十进制，10的幂只能是10、100、10000等等（第一位是1后面是0）
     * 二进制，2的幂只能是10、100、10000等等（第一位是1后面是0）
     * 所以转换成3进制，同理
     */
    public boolean isPowerOfThree1(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }

    /**
     * 数学法,log以10为第n的对数 / log以10位底3的对数
     *
     * @param n
     * @return
     */
    // public boolean isPowerOfThree2(int n) {
    // 这里仅仅用来作为数学表示，在计算机中无法准确表示这个表达式成立。
    // return Math.log10(n) / Math.log10(3) % 1 <= 2 * 精度;
    // }

    /**
     * 基于3是质数。
     * 因为3是质数，所以在一个范围内，比如Integer。3的最大幂/n，能整除，则n一定是3的幂
     * --------------
     * 扩展：如果是需要多次反复调用的话，其实用对照表是最好的选择
     * 毕竟Integer在正数范围内只有19个解
     */
    public boolean isPowerOfThree3(int n) {
        // step1，找到在取值范围内3的最大幂
        long max = 1;

        while (max * 3 < Integer.MAX_VALUE) {
            max *= 3;
        }
        // step2,取模
        return n > 0 && max % n == 0;
    }
}
