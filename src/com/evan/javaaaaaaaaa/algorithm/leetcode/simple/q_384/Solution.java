package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_384;

import java.util.Random;

public class Solution {

    private final int[] source;
    private int[] target;

    private Random random = new Random(47);


    public Solution(int[] nums) {
        this.source = nums;
        this.target = source.clone();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        this.target = source.clone();
        return target;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        long start = System.nanoTime();
        target = source.clone();
        for (int i = 0; i < target.length; i++) {
            int anchor = random.nextInt(target.length - i) + i;
            swap(i, anchor);
        }
        System.err.println("cost:" + (System.nanoTime() - start));
        return target;
    }

    private void swap(int h, int t) {
        if (h == t)
            return;
        target[h] ^= target[t];
        target[t] = target[h] ^ target[t];
        target[h] = target[h] ^ target[t];
    }

}
