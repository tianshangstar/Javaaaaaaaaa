package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_101;

import com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_104.TreeNode;

import java.util.LinkedList;

public class Solution {

    /**
     * 递归 + 双指针
     */
    public boolean isSymmetric(TreeNode root) {

        if (root == null) {
            return true;
        }

        return isSymmertric(root.left, root.right);
    }

    private boolean isSymmertric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        // 递归，对称比较
        return (left.val == right.val) && isSymmertric(left.left, right.right) && isSymmertric(left.right, right.left);
    }

    /**
     * 非递归
     */
    public boolean isSymmetric1(TreeNode root) {

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.push(root);
        queue.push(root);

        TreeNode left, right;

        // 借助队列咯
        while (!queue.isEmpty() && !queue.isEmpty()) {
            left = queue.pop();
            right = queue.pop();

            if (left == null && right == null) {
                continue;
            }

            if (left == null || right == null) {
                return false;
            }

            if (left.val != right.val) {
                return false;
            }

            queue.push(left.left);
            queue.push(right.right);

            queue.push(left.right);
            queue.push(right.left);

        }

        return true;
    }
}
