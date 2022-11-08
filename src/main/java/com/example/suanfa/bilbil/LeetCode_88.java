package com.example.suanfa.bilbil;

import java.util.Arrays;

public class LeetCode_88 {
    //https://leetcode.cn/problems/merge-sorted-array/
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[m + 1] = nums2[i];
        }
        Arrays.sort(nums1);
    }


    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n;
        int[] temp = new int[k];
        for (int index = 0, num1Index = 0, num2Index = 0; index < k; index++) {
            if (num1Index >= m) {
                temp[index] = nums2[num2Index++];
            } else if (num2Index >= n) {
                temp[index] = nums1[num1Index++];
            } else if (nums1[num1Index] < nums2[num2Index]) {
                temp[index] = nums1[num1Index++];
            } else {
                temp[index] = nums2[num2Index++];
            }

        }
        System.arraycopy(temp, 0, nums1, 0, k);
    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int k = m + n;
        for (int index = k - 1, num1Index = m - 1, num2Index = n - 1; index > 0; index--) {
            if (num1Index < 0) {
                nums1[index] = nums2[num2Index--];
            } else if (num2Index < 0) {
                break;
            } else if (nums1[num1Index] > nums2[num2Index]) {
                nums1[index] = nums1[num1Index--];
            } else {
                nums1[index] = nums2[num2Index--];
            }

        }

    }
}
