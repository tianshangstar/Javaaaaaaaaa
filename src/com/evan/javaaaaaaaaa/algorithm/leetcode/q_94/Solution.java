package com.evan.javaaaaaaaaa.algorithm.leetcode.q_94;

import com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_104.TreeNode;

import java.util.*;

/**
 * 中序；
 * 左 中 右
 */
public class Solution {

    // 解法1 递归
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        inorderTraversal(root, list);

        return list;
    }

    public void inorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
    }

    // 递归都是可以替换成循环的
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Deque<TreeNode> stack = new LinkedList();
        TreeNode cur = root;

        // 遍历二叉树
        while (cur != null || !stack.isEmpty()) {
            // 一直遍历左字数，并放入队列
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 把栈顶节点出栈，放入结果集
            TreeNode node = stack.poll();
            list.add(node.val);
            cur = node.right;
        }

        return list;
    }
}
