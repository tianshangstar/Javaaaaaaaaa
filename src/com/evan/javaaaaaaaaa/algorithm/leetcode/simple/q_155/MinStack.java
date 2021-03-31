package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_155;

import java.util.Deque;
import java.util.LinkedList;

public class MinStack {

    private Deque<Integer> sourceQueue;

    private Deque<Integer> minQueue;

    public MinStack() {
        sourceQueue = new LinkedList<>();
        minQueue = new LinkedList<>();
        // 这个操作有点骚，后面不用判断min队列空了
        minQueue.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        sourceQueue.push(val);
        // 没错，就是这里不用校验空了
        minQueue.push(Math.min(val, minQueue.peek()));
    }

    public int pop() {
        minQueue.pop();
        return sourceQueue.pop();
    }

    public int top() {
        return sourceQueue.peek();
    }

    public int getMin() {
        return minQueue.peek();
    }

}
