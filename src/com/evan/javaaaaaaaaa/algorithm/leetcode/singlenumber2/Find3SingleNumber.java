package com.evan.javaaaaaaaaa.algorithm.leetcode.singlenumber2;

/**
 * https://leetcode-cn.com/problems/single-number-ii/
 * <p>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * <p>
 * 思路:
 *
 * @see com.evan.javaaaaaaaaa.algorithm.leetcode.singlenumber.Find2SingleNumber
 */
public class Find3SingleNumber {

    // the correct result is 17
    private static int[] arr = {99, 17, 99, 6, 9, 9, 6, 99, 6, 9};

    public static void main(String[] args) {
        solution(arr);
    }

    /**
     * 我没想出来。。 想出来的办法复杂度是n²（先排序的不算）
     * <p>
     * 下面的算法来自leetcode
     */
    private static void solution(int[] arr) {
        // 思路：用a、b两个int来表示记录，如果同一位上出现3次，清零（这个怎么做是算法的核心）
        /*
         伪代码: ab ^ 0 = ab
                ab ^ ab = 0
         leetcode解释：它是一个逻辑电路，a、b变量中，相同位置上，分别取出一位，负责完成00->01->10->00，也就是开头的那句话，当数字出现3次时置零。
         ------假设 a : 10 b : 01 此时a ? b的运算结果应该为0；how？
         ------10 ^ (~01) = 0? a & b = 0?

         上面个两行的思路是错误的
         是用ab来表示同一位
         即：a = 1， b = 0，对应的二进制表示是10
         所以对应的表达式应该是：
         a  b   x   结果
         0  0 ^ 1   01
         0  1 ^ 1   10
         1  0 ^ 1   00
         对照上表达式，可以得出：
         a ? b ? x的规则
         再：
         ab ^ 1 = 01
         ab ^ 1 = 10
         ab ^ 1 = 11
         用ab同一位来表示真实计数的一位
         按照a高位，b低位的表示方法
         首先：b^x ---> 来记录x在每一位上的出现次数
              a^x ---> 来记录x在每一位上的出现次数


         其次：ab作为1位来表示，要满足
         a  b   value
         0  0   0
         0  1   1
         1  0   2
         1  1 -> 0 0 -> 0
         推导出：重要：a & ~b
         即：同0，异不变
         要求，第一次遇到： a=0;b=1; 第二次遇到： a=1;b=0;  第三次遇到： a=0;b=0
        */
        int a = 0;
        int b = 0;

        for (int x : arr) {
            // 最终解析：
            // b^x；记录这一位上出现的次数，如第一次b=1；第二次b=0；第三次b进位，b又等于1；所以要引入高位干扰:
            // 即： 00->01->10->00
            // 重要：b^x的结果为当前x在b位上出现的次数，a为高位，所以 b^x & (~a)即为当前b位的结果
            // 由于a来表示高位，b是低位，所以先计算低位
            b = (b ^ x) & (~a);
            a = (a ^ x) & (~b);
        }
        System.out.println(a); // 猜猜，为什么a是0
        System.out.println(b);
    }

}
