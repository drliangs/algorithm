package com.example.suanfa.bilbil;

import java.util.HashMap;

public class LeetCode_1 {
    //https://leetcode.cn/problems/two-sum/
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return nums;
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            Integer index = map.get(another);
            if (index != null) {
                ans[0] = index;
                ans[1] = i;
                break;

            } else {
                map.put(nums[i], i);

            }

        }
        return ans;

    }


}
