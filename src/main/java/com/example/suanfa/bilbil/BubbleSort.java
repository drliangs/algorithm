package com.example.suanfa.bilbil;

public class BubbleSort {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j + 1] < nums[j]) {
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }

}
