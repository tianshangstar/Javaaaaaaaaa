package com.evan.javaaaaaaaaa.algorithm.leetcode.singlenumber;

/**
 * https://leetcode-cn.com/problems/single-number/
 * <p>
 * 给定一个非空整数数组，除了某个元素只出现一（奇数）次以外，其余每个元素均出现两（偶数）次。找出那个只出现了一次的元素。
 */
public class Find1SingleNumber {

    private static int[] arr = {99, 17, 44, 17, 99, 44, 6, 9, 9, 6, 101, 99, 99};

    public static void main(String[] args) {
        solution(arr);
    }

    /**
     * 异或：
     * 交换律：a ^ b ^ c <=> a ^ c ^ b
     * <p>
     * 任何数于0异或为任何数 0 ^ n => n
     * <p>
     * 相同的数异或为0: n ^ n => 0
     * 88888888888888888888888888888888888888888888888888
     * 另一种办法：利用java set的数据唯一性（要自动装箱拆箱，肯定没直接^快）。代码略
     *
     * @param arr
     */
    private static void solution(int[] arr) {
        int result = 0;
        for (int i : arr) {
            result ^= i;
        }
        System.out.println(result);
    }

}
