package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_191;

public class Solution {

    private static int[] arr = new int[32];

    static {
        int anchor = 1;
        for (int i = 0; i < 32; i++) {
            arr[i] = anchor;
            anchor <<= 1;
        }
    }

    public int hammingWeight(int n) {
        int count = 0;
        for (int anchor : arr) {
            if ((anchor & n) == anchor) {
                count++;
            }
        }
        return count;
    }

}
