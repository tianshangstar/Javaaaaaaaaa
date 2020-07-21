package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_19;

public class RemoveNthFromEnd {

    public static void main(String[] args) {
        ListNode dummy = new ListNode(0);
        dummy.next = createNode();
        execute(dummy.next, 1);
        dummy.next = createNode();
        execute(dummy.next, 2);
        dummy.next = createNode();
        execute(dummy.next, 7);

    }

    private static void execute(ListNode head, int n) {
        Solution solution = new Solution();
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = solution.removeNthFromEnd(dummy.next, n);
        printListNode(head, String.format("after - remove[%d]", n));
    }

    private static ListNode createNode() {
        ListNode dummy = new ListNode(0);
        ListNode listNode = new ListNode(1);
        dummy.next = listNode;
//        for (int i = 2; i < 8; i++) {
//            listNode.next = new ListNode(i);
//            listNode = listNode.next;
//        }

        printListNode(dummy.next, "create");

        return dummy.next;
    }

    private static void printListNode(ListNode head, String tag) {
        System.err.println("\n" + tag);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (head != null) {
            sb.append(head.val).append(", ");
            head = head.next;
        }
        String p;
        int n = sb.length();
        if (n > 2) {
            p = sb.substring(0, sb.length() - 2) + "]";
        } else {
            p = sb.append("]").toString();
        }
        System.err.println(p);
        System.err.println("+++++++++++++++++++++++++++++++++++");
    }
}
