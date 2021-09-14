package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_13;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    static Map<Character, Integer> map = new HashMap<>();

    static {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    public int romanToInt(String s) {
        if (StringUtils.isBlank(s)) {
            return 0;
        }
        //I 可以放在 V (5)   和 X (10)   的左边，来表示 4 和 9。
        //X 可以放在 L (50)  和 C (100)  的左边，来表示 40 和 90。
        //C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。

        // 罗马数字的规律:两个相邻的位，
        // 右边比左边大，减
        // 右边比左边小，加
        char[] arr = s.toCharArray();

        int result = 0;

        for (int i = 0; i < arr.length; i++) {

            int value = map.get(arr[i]);

            // 右边比左边小
            if (i < arr.length - 1
                    && value < map.get(arr[i + 1])) {
                result -= value;
            } else {
                result += value;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.romanToInt("III");
    }
}
