package com.example.suanfa;

public class MyCircularQueue {
    int k = 0;
    int length = 0;
    ListNode head;
    ListNode tail;

    public MyCircularQueue(int k) {
        this.k = k;
    }

    public boolean enQueue(int value) {
        if(length >= k) return false;
        if(head == null){
            head = new ListNode(value);
            head.next = head;
            this.tail = head;
        }else{
            ListNode node = new ListNode(value);
            tail.next = node;
            node.next = head;
            tail = node;
        }
        length++;
        return true;
    }

    public boolean deQueue() {
        if(head == null) return false;
        if(length == 1) {
            head = null;
            tail = null;
        }else{
            ListNode newHead = head.next;
            tail.next = newHead;
            head = newHead;
        }
        length--;
        return true;
    }

    public int Front() {
        if(head == null) return -1;
        return head.value;
    }

    public int Rear() {
        if(tail == null) return -1;
        return tail.value;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean isFull() {
        return length == k;
    }

    public class ListNode{
        int value;
        ListNode next;
        public ListNode(int value){
            this.value = value;
        }
    }

}
