package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_98;

import com.alibaba.fastjson.JSON;
import com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_104.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {

    // 1, 递归，保证每一个子树都满足左<中<右
    public boolean isValidBST(TreeNode root) {

        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode treeNode, long leftValue, long rightValue) {
        if (treeNode == null) {
            return true;
        }

        if (treeNode.val <= leftValue || treeNode.val >= rightValue) {
            return false;
        }

        return isValidBST(treeNode.left, leftValue, treeNode.val) && isValidBST(treeNode.right, treeNode.val, rightValue);
    }


    // 中序遍历
    public boolean isValidBSTV2(TreeNode root) {

        Deque<TreeNode> treeNodeDeque = new LinkedList<>();

        // 终须遍历，所以第一个需要进行比较的节点位于最左分支最下
        long inorder = Long.MIN_VALUE;

        while (!treeNodeDeque.isEmpty() || root != null) {
            while (root != null) {
                treeNodeDeque.push(root);
                root = root.left;
            }

            root = treeNodeDeque.pop();
            if (root.val >= inorder) {
                return false;
            }

            inorder = root.val;
            root = root.right;
        }

        return true;
    }
}
