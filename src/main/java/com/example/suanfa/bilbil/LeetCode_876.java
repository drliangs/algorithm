package com.example.suanfa.bilbil;

//https://leetcode.cn/problems/middle-of-the-linked-list/
public class LeetCode_876 {


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


    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    public static ListNode kthNodeFromEnd(ListNode head, int kthNode) {
        if (kthNode <= 0 || head == null) return null;
        ListNode pTemp = head, pKtchNode = null;
        for (int count = 0; count < kthNode; count++) {
            if (pTemp != null) {
                pTemp = pTemp.next;
            }
        }
        while (pTemp != null) {
            if (pKtchNode == null) pKtchNode = head;
            else pKtchNode = pKtchNode.next;
            pTemp = pTemp.next;
        }
        return pKtchNode;

    }


}
