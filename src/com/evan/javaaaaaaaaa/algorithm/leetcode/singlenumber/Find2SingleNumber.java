package com.evan.javaaaaaaaaa.algorithm.leetcode.singlenumber;

/**
 * @see Find1SingleNumber
 * 进阶一下：如果一个数组中有两个非0数字，出现了奇数次呢？
 */
public class Find2SingleNumber {

    private static int[] arr = {99, 17, 44, 17, 99, 44, 6, 9, 9, 6, 101, 99, 99, 77};

    public static void main(String[] args) {
        solution(arr);
    }

    private static void solution(int[] arr) {
        int ga = 0;
        // 得到出现了奇数次的两个数字的异或结果
        for (int i : arr) {
            ga ^= i;
        }
        // 定义两个变量接收出现了奇数次的值
        int r1 = ga, r2 = ga;
        // how???
        /* https://mp.weixin.qq.com/s?__biz=MzUyNjQxNjYyMg==&mid=2247484505&idx=1&sn=4c1c056dd4852c3b4b1ead51c90a9b2d&chksm=fa0e6bd8cd79e2ce8188dcdd8843a5d071248906bfb8971c62d513dbd69b816acc191a78e4b2&scene=21#wechat_redirect
            上面的图解讲的很好，总结一下：
            所有数异或的结果，为出现单数次的两个结果的^
            那么：这两个出现单数次的结果，在二进制表示上，至少有一位是不同的。根据异或的特性，受限要找到ga中任意为1的那一位
            思路：将任意位，按照0/1分为两组，分别与上面的异或结果再进行异或，即可以得到出现了奇数次的两个值(a ^ b ^c ^ b ^ c = a)
         */
        System.out.println("ga = " + ga);

        int anchor = 1;
        // 思路很简单，一直与就行了，如果结果不为0，那么将anchor右移，继续与，知道找到结果为1的哪一个
        while (0 == (ga & anchor)) {
            anchor <<= 1;
        }
        System.out.println("anchor = " + anchor);

        for (int i : arr) {
            if (0 == (i & anchor)) { // 如果最低位为0
                System.out.println("偶数 : " + i);
                r1 ^= i;
            } else {
                System.out.println("奇数 : " + i);
                r2 ^= i;
            }
        }

        System.out.println(r1);
        System.out.println(r2);
    }
}
