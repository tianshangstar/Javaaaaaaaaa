package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_237;

public class Solution {
    public void deleteNode(ListNode node) {

        // 看solution1吧，没必要挨个换值之后断尾。
        while (node.next != null) {
            node.val = node.next.val;
            if (node.next.next == null) {
                node.next = null;
            } else {
                node = node.next;
            }
        }
        node.next = null;
    }
}
