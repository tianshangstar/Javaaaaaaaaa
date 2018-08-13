package com.evan.javaaaaaaaaa.algorithm.leetcode.addtwonumbers;

/**
 * Created by evan01.zhang on 2018/2/9.
 */
public class BetterOne {

    public AddTwoNumbers.ListNode addTwoNumbers(AddTwoNumbers.ListNode l1, AddTwoNumbers.ListNode l2) {
        AddTwoNumbers.ListNode dummyHead = new AddTwoNumbers.ListNode(0);
        AddTwoNumbers.ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new AddTwoNumbers.ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new AddTwoNumbers.ListNode(carry);
        }
        return dummyHead.next;
    }

}
