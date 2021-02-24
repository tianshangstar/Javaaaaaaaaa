package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_268;

public class MissingNumber {

    public static void main(String[] args) {

        int[] nums1 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        int[] nums2 = {3,0,1};
        int[] nums3 = {0,1};
        int[] nums4 = {0};

        Solution solution = new Solution();

        System.err.println(solution.missingNumber(nums1));
        System.err.println(solution.missingNumber(nums2));
        System.err.println(solution.missingNumber(nums3));
        System.err.println(solution.missingNumber(nums4));
    }

}
