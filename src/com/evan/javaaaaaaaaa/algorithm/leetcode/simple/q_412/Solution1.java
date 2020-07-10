package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_412;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 这个是看题解做的；
 * 关于为啥这样：如果不只有3和5，有10个key，20个key需要进行这种操作呢？
 * 所以替换为hash
 * 另：需要保证有序，所以换成linkedHashMap
 */
public class Solution1 {

    public static List<String> solution(int n) {

        List<String> result = new ArrayList<>();

//        Map<Integer, String> hash = new HashMap<>();
        Map<Integer, String> hash = new LinkedHashMap<>();
        hash.put(3, "Fizz");
        hash.put(5, "Buzz");

        for (int i = 1; i <= n; i++) {
            String str = "";
            for (Integer key : hash.keySet()) {
                if (i % key == 0) {
                    str += hash.get(key);
                }
            }
            if (StringUtils.isBlank(str)) {
                str = String.valueOf(i);
            }
            result.add(str);
        }
        return result;
    }
}
