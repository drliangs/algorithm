package com.example.suanfa;

import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.List;

public class Day1 {
    public int majorityElement(int[] nums) {
        int temp = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (temp == nums[i]) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    temp = nums[i + 1];
                }
            }
        }
        return temp;

    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode res = list1.val < list2.val ? list1 : list2;
        res.next = mergeTwoLists(res.next, list1.val >= list2.val ? list1 : list2);
        return res;
    }


    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode node = head;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                node = node.next = list1;
                list1 = list1.next;
            } else {
                node = node.next = list2;
                list2 = list2.next;
            }
        }
        if (list1 == null) {
            node.next = list2;
        } else {
            node.next = list1;
        }
        return head.next;
    }

    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum > 0) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            res = Math.max(res, sum);
        }
        return res;

    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int[] nums = new int[length];
        int j = 0, i = 0, index = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                nums[index++] = nums1[i];
                i++;
            } else {
                nums[index++] = nums2[j];
                j++;
            }
        }
        int temp = Math.abs(nums1.length - nums2.length);
        int[] arr = nums1.length < nums2.length ? nums2 : nums1;
        for (int k = temp; k < arr.length; k++) {
            nums[index++] = arr[k];
        }


        int mid = length / 2;
        if (length % 2 == 0) {
            return (double) (nums[mid - 1] + nums[mid]) / 2;
        } else {
            return nums[mid];
        }

    }

    public int[] searchRange(int[] nums, int target) {
        int index = binarySearch(nums, target);
        if (index == -1) {
            return new int[]{-1, -1};
        }
        int left = index;
        int right = index;
        while (left - 1 >= 0 && nums[left - 1] == nums[index]) { // 防止数组越界。逻辑短路，两个条件顺序不能换
            left--;
        }
        // 向左滑动，找右边界
        while (right + 1 < nums.length && nums[right + 1] == nums[index]) { // 防止数组越界。
            right++;
        }
        return new int[]{left, right};
    }

    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int b = 2, a = 1, temp;

        for (int i = 3; i <= n; i++) { //4
            temp = a;  //4 5
            a = b;  //5 // 6
            b += temp;  //7 //13
        }
        return b;
    }

    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int min = prices[0], max = 0;
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }

    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        if (index < nums.length) {
            for (int i = index; i < nums.length; i++) {
                nums[index++] = 0;
            }
        }
    }


    private static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {5, 7, 7, 8, 8, 10};
        System.out.println(binarySearch(a, 8));

    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode next = temp.next;
            ListNode nextNext = temp.next.next;
            temp.next = nextNext;
            next.next = nextNext.next;
            nextNext.next = next;
            temp = next;
        }
        return dummyHead.next;
    }


    public ListNode sw(ListNode head) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode prev = dummyNode;
        while (prev.next != null && prev.next.next != null) {
            ListNode temp = head.next.next;
            prev.next = head.next;
            head.next.next = head;
            head.next = temp;
            prev = head;
            head = head.next;
        }
        return dummyNode.next;
    }

 
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


}
