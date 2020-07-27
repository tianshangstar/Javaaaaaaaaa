package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_104;

import javafx.util.Pair;

import java.util.Stack;

public class Solution {

    /**
     * 深度优先；栈
     * 先实现，后面再说
     * --------------
     * 思路大概这样：从左边开始压栈（好像叫左先序）
     * 压到底，同步计算栈深度；
     * 弹出，如果有右节点，继续压，计算栈深度
     * 找出栈深度最大值。
     * <p>
     * -------------------------
     * 深度优先。
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();

        int maxDepth = 0;
        Pair<TreeNode, Integer> pair;
        if (root != null) {
            pair = new Pair<>(root, ++maxDepth);
            stack.push(pair);
        }
        int count = 0;
        while (!stack.isEmpty()) {
            count ++;
            pair = stack.pop();
            root = pair.getKey();
            int dep = pair.getValue();
            maxDepth = Math.max(dep, maxDepth);
            if (root.left != null) {
                stack.push(new Pair<>(root.left, dep + 1));
            }
            if (root.right != null) {
                stack.push(new Pair<>(root.right, dep + 1));
            }
        }
        System.err.println(count);
        return maxDepth;
    }
}
