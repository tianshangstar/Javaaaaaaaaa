package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_412;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class FizzBuzz {

    public static void main(String[] args) {
//        List<String> result = Solution.solution(30);
        List<String> result = Solution1.solution(30);
        System.err.println(JSON.toJSONString(result));
    }

}
