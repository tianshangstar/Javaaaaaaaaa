package com.evan.javaaaaaaaaa.algorithm.leetcode.addtwonumbers;

/**
 * Created by evan01.zhang on 2018/2/8.
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 * 原题目地址:https://leetcode.com/problems/add-two-numbers/solution/
 */
public class AddTwoNumbers {


    /**
     * Definition for -linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return val + (next == null ? "" : "->" + next.toString());
        }
    }

    public static void main(String[] args) {
        // 链表342
        ListNode node1_0 = new ListNode(2);
        ListNode node1_1 = new ListNode(4);
        ListNode node1_2 = new ListNode(3);
        node1_1.next = node1_2;
        node1_0.next = node1_1;

        // -------------------------------------

        // 链表465
        ListNode node2_0 = new ListNode(5);
        ListNode node2_1 = new ListNode(6);
        ListNode node2_2 = new ListNode(4);
        node2_1.next = node2_2;
        node2_0.next = node2_1;

        System.out.println(node1_0.toString());
        System.out.println(node2_0.toString());

        System.out.println(addTwoNumbers(node1_0, node2_0));

    }

    /*
            链表2->4->3 to int : 342
            链表5->6->4 to int : 465

                 3      4      2
               + 4      6      5
              ------------
                 8(7+1) 0(10)  7
        ------------------------------------------
            不需要还原出数字，从最高位开始，按位相加，封10下一位计算结果+1

     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // step 1 找出长度为最长的链表（默认高位不为0，链表表示的数字是0）
        int l1Size = getListNodeSize(l1), l2Size = getListNodeSize(l2);
        if (l1Size > l2Size) {
            return addTwoListNode(l1, l2, l1Size, l2Size);
        } else {
            return addTwoListNode(l2, l1, l2Size, l1Size);
        }
    }

    private static ListNode addTwoListNode(ListNode large, ListNode small, int largeSize, int smallSize) {

        if (largeSize == 0) {
            return large;
        }

        if (smallSize == 0) {
            return small;
        }

        ListNode resultNode = null;
        ListNode temp = null;
        boolean needCarry = false;
        ListNode tempLarge = large, tempSmall = small;
        int positionSum = 0;


        // 思路，不还原数字，按照链表，从起始节点开始求和，逢10下个节点求和进位
        for (int i = 0; i < largeSize; i++) {
            //求和，判断进位
            if (tempSmall == null) {
                positionSum = tempLarge.val + (needCarry ? 0 : 1);
            } else {
                positionSum = tempLarge.val + tempSmall.val + (needCarry ? 1 : 0);
            }
            //逢10，下个节点求和进位
            if (positionSum >= 10) {
                positionSum %= 10;
                needCarry = true;
            } else {
                needCarry = false;
            }

            // 创建返回链表
            if (i == 0) { //起始节点
                temp = new ListNode(positionSum);
                resultNode = temp;
            } else {
                temp = (temp.next = new ListNode(positionSum));
            }

            // 链表遍历
            tempLarge = tempLarge.next;
            tempSmall = tempSmall.next;
        }
        return resultNode;
    }

    public static int getListNodeSize(ListNode l) {
        int nodeSize = 0;

        ListNode tempNode = l;

        while (tempNode != null) {
            nodeSize++;
            tempNode = tempNode.next;
        }

        return nodeSize;
    }
}
