package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_412;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";

    public static List<String> solution(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                result.add(FIZZ + BUZZ);
            } else if (i % 3 == 0) {
                result.add(FIZZ);
            } else if (i % 5 == 0) {
                result.add(BUZZ);
            } else {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }
}
