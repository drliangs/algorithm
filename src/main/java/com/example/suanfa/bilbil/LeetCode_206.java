package com.example.suanfa.bilbil;


//https://leetcode.cn/problems/reverse-linked-list/
public class LeetCode_206 {
    static public class ListNode    {
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

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = head.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
