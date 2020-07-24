package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_204;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 没做过，梳理思路
 * 质数的定义是不能被除了1和他自身整除。
 * 1、避免重复计算，所以需要把数字记下来，每排除一个删除一个，因为要计数所以数组不太方便
 * ，这里自己考虑用HashSet
 * 2、如果从1遍历到n，那么复杂度是O(n²)，明显太高了，咋优化呢。。
 * 其实从1遍历到n/2就够了啊。
 * <p>
 * 时间不达标啊亲
 * 优化1：n/2 -> sqrt(n)
 * 优化2: 位图思路 hashSet -> char[]
 */
public class Solution {
    public int countPrimes(int n) {
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i < n; i++) {
            set.add(i);
        }

        for (int i = 2; i * i < n; i++) {
            Iterator<Integer> it = set.iterator();
            while (it.hasNext()) {
                int val = it.next();
                if (val % i == 0 && val != i) {
                    it.remove();
                }
            }
        }
//        System.err.println("n=" + n);
//        System.err.println("count=" + set.size());
//        System.err.println(JSON.toJSONString(set));
//        System.err.println("++++++++++++++++++++++++++++++++");
        return set.size();

    }
}
