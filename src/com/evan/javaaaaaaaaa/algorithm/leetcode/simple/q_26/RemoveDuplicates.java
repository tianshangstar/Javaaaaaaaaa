package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_26;

import com.alibaba.fastjson.JSON;

public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 4, 5};
        Solution solution = new Solution();
        int len = solution.removeDuplicates(arr);
        System.err.println("len = " + len);
        System.err.println(JSON.toJSONString(arr));
    }

}
