package com.example.suanfa.bilbil;


import java.util.ArrayList;
import java.util.List;

///https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/
public class LeetCode_448 {


    public List<Integer> findDisappearedNumbers(int[] nums) {
        int N = nums.length;
        for (int num : nums) {
            int x = (num - 1) % N;
            nums[x] += N;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (nums[i] <= N) {
                result.add(i + 1);
            }
        }
        return result;
    }


}
