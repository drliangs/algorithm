package com.example.suanfa.bilbil;


//https://leetcode.cn/problems/intersection-of-two-linked-lists/
public class LeetCode_160 {
    static public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        int L1 = 0, L2 = 0, differ;
        ListNode head1 = headA, head2 = headB;
        while (head1 != null) {
            L1++;
            head1 = head1.next;
        }
        while (head2 != null) {
            L2++;
            head2 = head2.next;
        }
        if (L1 < L2) {
            head1 = headB;
            head2 = headA;
            differ = L2 - L1;
        } else {
            head1 = headA;
            head2 = headB;
            differ = L1 - L2;
        }
        for (int i = 0; i < differ; i++) {
            head1 = head1.next;
        }
        while (head1 != null || head2 != null) {
            if (head1 == head2) {
                return head1;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return null;

    }
}
