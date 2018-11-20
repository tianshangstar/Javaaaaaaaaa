package com.evan.javaaaaaaaaa.algorithm.leetcode;

import java.util.*;

/**
 * Created by evan01.zhang on 2018/5/4.
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Examples:
 * <p>
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * <p>
 * Given "bbbbb", the answer is "b", with the length of 1.
 * <p>
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 */
public class LongestSubstr {
    public static void main(String[] args) {
        String target = "eraasdfaeddsasdsasf";// should output 'asdf'
        System.out.println(lengthOfLongestSubstring(target));
        System.out.println(lengthOfLongestSubstring1(target));
        System.out.println(lengthOfLongestSubstring2(target));
        System.out.println(lengthOfLongestSubstring3(target));
    }

    /**
     * 方法1：暴力强解，强行解除字符串的每一个符合
     * 条件的子字符串并比较长度（我想到的也是这个办法）
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    public static boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }

    /**
     * 方法2:sliding window(滑动窗口)
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * 方法3：优化滑动窗口
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);

//            System.out.println("j = " + j + ", i = " + i + "; ans = " + ans
//                    + ", charAt(j) = " + s.charAt(j)
//                    + ", s[j, i] = " + ((j > i) ? s.subSequence(i, j + 1) : "")
//                    + ", map = " + map
//            );
        }
        return ans;
    }

    /**
     * 字符数组
     * 和解法3同理，只是由字符数组替换掉map
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
            System.out.println("j = " + j + ", i = " + i + "; ans = " + ans
                    + ", charAt(j) = " + s.charAt(j)
                    + ", s[j, i] = " + ((j > i) ? s.subSequence(i, j + 1) : "")
            );
        }
        return ans;
    }
}
