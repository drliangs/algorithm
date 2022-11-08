package com.example.suanfa;

import java.util.HashMap;
import java.util.Map;

public class Day6 {

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return head;
        Map<Node, Node> map = new HashMap<>();
        for (Node cur = head; cur != null; cur = cur.next) {
            map.put(cur, new Node(cur.val));
        }
        for (Node cur = head; cur != null; cur = cur.next) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
        }
        return map.get(head);

    }

    public Node copyRandomListz(Node head) {
        if (head == null) {
            return head;
        }
        for (Node node = head, copy; node != null; node = node.next.next) {
            copy = new Node(node.val);
            copy.next = node.next;
            node.next = copy;
        }
        for (Node node = head; node != null; node = node.next.next) {
            if (node.random != null) {
                node.next.random = node.random.next;
            }
        }
        Node newHead = head.next;
        for (Node node = head, temp; node != null && node.next != null; ) {
            temp = node.next;
            node.next = temp.next;
            node = temp;
        }
        return newHead;

    }


}
