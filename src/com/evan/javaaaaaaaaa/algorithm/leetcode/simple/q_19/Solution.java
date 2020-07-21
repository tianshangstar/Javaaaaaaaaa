package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_19;


public class Solution {
    /**
     * 题目建议用一次循环完成.............
     * 链表，长度位置，所以肯定需要记录长度；
     * 需要返回head节点，所以注定需要一个指针来指向head节点作为返回结果
     * <p>
     * 难点：怎么识别是倒数第几个节点呢？
     * 难点：怎么在一次循环中完成呢？
     * <p>
     * * 没想到，下面来自参考答案之后
     * <p>
     * 设n = 2;需要删除节点6
     * {1, 2, 3, 4, 5, 6, 7}
     * t
     * c
     * step1:t先移动n
     * {1, 2, 3, 4, 5, 6, 7}
     * 移 移   t              ----移动2次
     * c
     * {1, 2, 3, 4, 5, 6, 7}
     * 移 移 移 移 移 移 移  t  ----移动5次
     * 移 移 移 移 移 c        ----移动5次
     * ------------------------------------
     * 总结：其实好像滑动窗口有木有？
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // t的目标有是帮助c移动到需要删除的节点的前一个
        ListNode t, c, ac;
        ac = new ListNode(0);
        ac.next = head;
        t = c = ac;
        for (int i = 1; i < n + 1; i++) {
            t = t.next;
        }

        while (t.next != null) {
            t = t.next;
            c = c.next;
        }
        c.next = c.next.next;
        return ac.next;
    }
}
