package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_191;

public class HammingWeight {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 11 -> 1111
        System.err.println(solution.hammingWeight(11));
        // 11 -> 1111
        System.err.println(solution.hammingWeight(11));
        // -1 -> 11111111111111111111111111111111
        System.err.println(solution.hammingWeight(-1));
        // -1 -> 11111111111111111111111111111101
        System.err.println(solution.hammingWeight(-3));
    }
}
