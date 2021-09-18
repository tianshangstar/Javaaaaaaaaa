package com.evan.javaaaaaaaaa.algorithm.leetcode.q_179;

public class Solution {

    public String largestNumber(int[] nums) {
        // 类似堆排序的思路
        // 也就是说，可以对原数组进行排序，只不过排序规则不一样
        // 正常数组排序是按照数值大小，这里排序需要按照
        // "比较每一个数字的最高位，最高位大的在前面，如果相同则比较次高位。"

        // 简单点，冒个泡
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (needSwap(nums[j], nums[j + 1])) {
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        // 这段我抄的，之前是先拼在片段。
        // 排序好如果第一个是0确实没必要继续了
        if (nums[0] == 0) {
            return "0";
        }

        for (int num : nums) {
            stringBuilder.append(num);
        }

        return stringBuilder.toString();
    }

    // 按照最高位规则比较两个数
    // 如果xy < yx 则需要交换
    public boolean needSwap(int x, int y) {
        // 如果除10找高位有个问题，低位会丢失，咋弄？
        // 那是否可以反其道而行之，乘以10一直到两个数字的位数一样？ 之后做差就可以比较大小了吧
        // 那他么怎么判断两个数位数一致？？？？？ 这破题这么多坑？

        // 假设 x * fx恰好比y多一位，则 x * fx + y就表示拼接结果， fy同理
        int fx = 10, fy = 10;

        while (fx <= x) {
            fx *= 10;
        }

        while (fy <= y) {
            fy *= 10;
        }

        // 由于限定死了 0 <= nums[i] <= 109
        // x 在前拼接最大值：
        // x * fy + y
        // y 在前拼接最大值
        // y * fx + x

        return ((y * fx + x) - (x * fy + y)) > 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int nums[] = {10, 2};
        System.err.println(solution.largestNumber(nums));
    }
}
