package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_155;

public class TestMinStack {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        minStack.push(-1);
        minStack.push(5);
        minStack.push(7);
        minStack.push(-2);
        minStack.push(3);


        System.err.println("min : " + minStack.getMin());

        System.err.println(minStack.pop());
        System.err.println("min : " + minStack.getMin());

        System.err.println(minStack.pop());
        System.err.println("min : " + minStack.getMin());

        System.err.println(minStack.pop());
        System.err.println("min : " + minStack.getMin());

        System.err.println(minStack.pop());

    }
}
