package com.example.suanfa.bilbil;


//https://leetcode.cn/problems/palindrome-linked-list/
public class LeetCode_234 {

    static public class ListNode {
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

    public static boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast != null) {
            slow = slow.next;
        }
        slow = reverse(slow);
        fast = head;
        while (slow != null) {
            if (slow.val != fast.val) return false;
            slow = slow.next;
            fast = fast.next;
        }

        return true;
    }

    private static ListNode reverse(ListNode slow) {
        ListNode pre = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }
        return pre;
    }
}
