package com.example.suanfa.bilbil;

//https://leetcode.cn/problems/linked-list-cycle-ii/
public class LeetCode_142 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode slow = head, fast = head;
        boolean loopExit = false;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                loopExit = true;
                break;
            }
        }
        if (loopExit) {
            slow = head;
            while (slow != fast) {
                fast = fast.next;
                slow = slow.next;
            }
            return slow;
        }
        return null;
    }


}
