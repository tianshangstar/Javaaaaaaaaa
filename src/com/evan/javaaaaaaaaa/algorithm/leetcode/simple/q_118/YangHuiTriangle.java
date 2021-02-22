package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_118;

import com.alibaba.fastjson.JSON;

public class YangHuiTriangle {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.err.println(JSON.toJSONString(solution.generate(5)));
        System.err.println(JSON.toJSONString(solution.generate(1)));
        System.err.println(JSON.toJSONString(solution.generate1(5)));
        System.err.println(JSON.toJSONString(solution.generate1(1)));
    }

}
