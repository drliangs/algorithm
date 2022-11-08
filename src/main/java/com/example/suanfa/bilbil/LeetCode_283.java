package com.example.suanfa.bilbil;

//https://leetcode.cn/problems/move-zeroes/
public class LeetCode_283 {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }

    }

    public void moveZeroes1(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int right = 0, left = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }

    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }


}
