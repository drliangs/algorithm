package com.example.suanfa.bilbil;


//https://leetcode.cn/problems/linked-list-cycle/
public class LeetCode_141 {


    public boolean hasCycle(LeetCode_21.ListNode head) {
        if (head == null) return false;
        LeetCode_21.ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }


}
