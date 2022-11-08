package com.example.suanfa.bilbil;

import java.util.HashMap;
import java.util.Map;

public class LetCode_70 {


    //https://leetcode.cn/problems/climbing-stairs/
    public int climbStairs(int n) {

        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    Map<Integer, Integer> storeMap = new HashMap<>();

    public int climbStairs1(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (null != storeMap.get(n)) {
            return storeMap.get(n);
        } else {
            int result = climbStairs1(n - 1) + climbStairs1(n - 2);
            storeMap.put(n, result);
            return result;
        }
    }

    public int climbStairs2(int n) {
        if (n == 2) return 2;
        if (n == 1) return 2;
        int result = 0;
        int pre = 2;
        int prePre = 1;
        for (int i = 3; i < n; i++) {
            result = pre + prePre;
            prePre = pre;
            pre = result;
        }
        return result;
    }


}
