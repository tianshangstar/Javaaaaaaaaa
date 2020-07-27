package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_104;

public class MaxDepth {
    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode head = new TreeNode(3);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        head.left = left;
        head.right = right;
        TreeNode left1 = new TreeNode(15);
        TreeNode right1 = new TreeNode(7);
        right.right = right1;
        right.left = left1;


        System.err.println(solution.maxDepth(head));
    }

}
