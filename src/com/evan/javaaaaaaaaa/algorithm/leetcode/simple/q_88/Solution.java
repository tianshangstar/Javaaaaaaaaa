package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_88;

public class Solution {
    /**
     * 双指针正向实现失败，
     * 参照官解后自己实现一次加深印象。
     *
     * @param nums1
     * @param m     代表nums1中有效数组的个数
     * @param nums2
     * @param n     代表n的有效数组个数
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;
        // set pointer for nums1
        int p = m + n - 1;

        // while there are still elements to compare
        while ((p1 >= 0) && (p2 >= 0))
            // compare two elements from nums1 and nums2
            // and add the largest one in nums1
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];

        // add missing elements from nums2
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }
}
