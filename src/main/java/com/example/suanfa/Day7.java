package com.example.suanfa;

import java.util.ArrayList;
import java.util.List;

public class Day7 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, ans, 0);
        return ans;
    }

    private void dfs(int[] nums, List<List<Integer>> ans, int cur) {
        if (cur == nums.length) {
            List<Integer> reuslt = new ArrayList<>();
            for (int num : nums) {
                reuslt.add(num);
            }
            ans.add(reuslt);
        } else {
            for (int i = cur; i < nums.length; i++) {
                if (canSweap(nums, cur, i)) {
                    swap(nums, cur, i);
                    dfs(nums, ans, cur + 1);
                    swap(nums,cur,i);
                }
            }
        }
    }

    private void swap(int[] nums, int cur, int i) {
        int temp = nums[cur];
        nums[cur] = nums[i];
        nums[i] = temp;
    }

    private boolean canSweap(int[] nums, int cur, int i) {
        for (int j = cur; j < i; j++) {
            if (nums[j] == nums[i]) return false;
        }
        return true;
    }

}
