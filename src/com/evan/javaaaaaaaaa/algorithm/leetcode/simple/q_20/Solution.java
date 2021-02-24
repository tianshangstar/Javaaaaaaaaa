package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_20;

import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public boolean isValid(String s) {

        Deque<Character> stack = new LinkedList<>();
        char[] arr = s.toCharArray();
//        '('，')'，'{'，'}'，'['，']'
        // 括号对的处理，参照官解，可以用map，
        // key : ) value : (
        // 当然反过来也可以。
        for (char c : arr) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }

            if (stack.isEmpty()) {
                return false;
            }

            char last = stack.pop();
            if ((c == ')' && last == '(')
                    || (c == '}' && last == '{')
                    || (c == ']' && last == '[')) {
                continue;
            }
            return false;
        }

        return stack.isEmpty();
    }
}
