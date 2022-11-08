package com.example.suanfa.bilbil;

public class LeetCode_21 {

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


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode resultNode = new ListNode();
        ListNode p = resultNode;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }
        if (list1 != null) {
            p.next = list1;
        }
        if (list2 != null) {
            p.next = list2;
        }
        return resultNode.next;
    }


    //2->3->5
    //1->6->8
    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode resultNode = list1.val < list2.val ? list1 : list2;
        resultNode.next = mergeTwoLists1(resultNode.next, list1.val >= list2.val ? list1 : list2);
        return resultNode;
    }


}
