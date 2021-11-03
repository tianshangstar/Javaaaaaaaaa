package com.evan.javaaaaaaaaa.algorithm.leetcode.sword_offer.so_q_60;

import com.alibaba.fastjson.JSON;

import java.util.UUID;

public class DicesTest {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.err.println(JSON.toJSONString(solution.dicesProbability(1)));
        System.err.println(JSON.toJSONString(solution.dicesProbability(2)));

        Solution1 solution1 = new Solution1();

        System.err.println(JSON.toJSONString(solution.dicesProbability(1)));
        System.err.println(JSON.toJSONString(solution.dicesProbability(2)));

    }

}
