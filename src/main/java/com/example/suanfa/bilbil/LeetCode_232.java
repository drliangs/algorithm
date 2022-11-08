package com.example.suanfa.bilbil;

import java.util.Stack;
//https://leetcode.cn/problems/implement-queue-using-stacks/
public class LeetCode_232 {
    Stack<Integer> inStack = new Stack<>();
    Stack<Integer> outStack = new Stack<>();

    public LeetCode_232() {

    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if (outStack.isEmpty()) {
            in2Out();
        }
        return outStack.pop();
    }

    public int peek() {
        if (outStack.isEmpty()) {
            in2Out();
        }
        return outStack.peek();
    }

    private void in2Out() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }

    public boolean empty() {
        return inStack.isEmpty() & outStack.empty();
    }
}
