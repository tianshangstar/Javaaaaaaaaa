package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_98;

import com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_104.TreeNode;

public class ValidBST {

    public static void main(String[] args) {
        Solution solution = new Solution();

//        TreeNode treeNode = new TreeNode(2);
//        treeNode.left = new TreeNode(1);
//        treeNode.right = new TreeNode(3);
//
//        System.err.println(solution.isValidBSTV2(treeNode));


        TreeNode treeNode1 = new TreeNode(5);
        TreeNode left = new TreeNode(4);
        treeNode1.left = left;

        TreeNode right = new TreeNode(6);
        treeNode1.right = right;

        right.left = new TreeNode(3);
        right.right = new TreeNode(7);

        System.err.println(solution.isValidBSTV2(treeNode1));

    }
}
