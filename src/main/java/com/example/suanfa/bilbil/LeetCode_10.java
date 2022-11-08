package com.example.suanfa.bilbil;


//https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/
public class LeetCode_10 {

    public int fib(int n) {
        if (n == 0 || n == 1)
            return n;

        int a = 1, b = 0;
        for (int i = a; i < n; i++) {
            a = a + b;
            b = a - b;
            a %= 1000000007;

        }
        return a;
    }


}
