package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_237;

public class Solution1 {
    public void deleteNode(ListNode node) {
        // 不需要循环啊。。
        // 换值，改变一个指针的指向就够了啊。
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
