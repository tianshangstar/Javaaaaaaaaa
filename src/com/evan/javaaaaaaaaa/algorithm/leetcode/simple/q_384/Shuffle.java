package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_384;

import com.alibaba.fastjson.JSON;

public class Shuffle {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        Solution solution = new Solution(arr);

        for (int i = 0; i < 3; i++) {
            System.err.println("------- " + i + " -------");
            System.err.println(JSON.toJSONString(solution.shuffle()));
            System.err.println(JSON.toJSONString(solution.reset()));
        }
    }
}
