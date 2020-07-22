package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_88;

import com.alibaba.fastjson.JSON;

public class ArrayMerge {
    public static void main(String[] args) {
        int[] nums1 = {4, 0, 0, 0, 0, 0};
        int[] nums2 = {1, 2, 3, 5, 6};

        Solution solution = new Solution();
        solution.merge(nums1, 1, nums2, 5);
        System.err.println(JSON.toJSONString(nums1));
        System.err.println(JSON.toJSONString(nums2));
        System.err.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

        int[] nums3 = {4, 5, 6, 0, 0, 0};
        int[] nums4 = {1, 2, 3};

        solution.merge(nums3, 1, nums4, 0);
        System.err.println(JSON.toJSONString(nums3));
        System.err.println(JSON.toJSONString(nums4));
        System.err.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

        int[] nums5 = {4, 0, 0, 0};
        int[] nums6 = {1, 2, 3};

        solution.merge(nums5, 1, nums6, 3);
        System.err.println(JSON.toJSONString(nums5));
        System.err.println(JSON.toJSONString(nums6));
        System.err.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

        int[] nums7 = {4};
        int[] nums8 = {};

        solution.merge(nums7, 1, nums8, 0);
        System.err.println(JSON.toJSONString(nums7));
        System.err.println(JSON.toJSONString(nums8));

    }
}
