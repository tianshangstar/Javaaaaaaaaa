package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_326;

import java.util.Arrays;
import java.util.List;

public class PowerOfThree {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(0, 1, 3, 9, 1162261467);

        Solution solution = new Solution();

        list.forEach(item -> System.err.println(solution.isPowerOfThree(item)));
        list.forEach(item -> System.err.println(solution.isPowerOfThree1(item)));
        list.forEach(item -> System.err.println(solution.isPowerOfThree3(item)));

    }
}
