package com.example.suanfa;

import java.util.*;

public class Day5 {

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

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public ListNode removeZeroSumSublists(ListNode head) {
        if (head == null) return null;
        int sum = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            if ((sum += cur.val) == 0) {
                return removeZeroSumSublists(head.next);
            }
        }

        head.next = removeZeroSumSublists(head.next);
        return head;
    }


    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        return isSubPath(head, root.left) ||
                isSubPath(head, root.right) ||
                calcuteNode(head, root);
    }

    private boolean calcuteNode(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        if (head.val == root.val) {
            return calcuteNode(head.next, root.left) || calcuteNode(head.next, root.right);
        }
        return false;
    }


    public ListNode swapNodes(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        ListNode first = head;
        // 用来定位倒数第k个节点
        ListNode last = head;
        int count = 1;
        while (cur.next != null) {
            if (count < k) {
                first = first.next;
            } else {
                last = last.next;
            }
            count++;
            cur = cur.next;
        }
        count = first.val;
        first.val = last.val;
        last.val = count;
        return head;

    }

    //[6,8,4,1,9,6,6,10,6]
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null) {
            return new int[]{-1, -1};
        }

        ListNode current = head.next;
        Deque<Integer> deque = new ArrayDeque<>();
        int last = head.val, minDistance = Integer.MAX_VALUE;

        for (int i = 0; current.next != null; i++) {
            if ((last > current.val && current.val < current.next.val) || (last < current.val && current.val > current.next.val)) {
                if (!deque.isEmpty()) {
                    minDistance = Math.min(minDistance, i - deque.getFirst());
                }
                deque.addLast(i);
            }
            last = current.val;
            current = current.next;
        }

        if (deque.size() <= 1) {
            return new int[]{-1, -1};
        } else {
            return new int[]{minDistance, deque.getLast() - deque.getFirst()};
        }


    }

    public ListNode reverseEvenLengthGroups(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int len = list.size();
        for (int i = 0, n = 1; i < len; n++) {
            int j = Math.min(len - 1, i + n - 1);
            //组长度为偶数，交换
            if ((j - i + 1) % 2 == 0) {
                reverse(list, i, j);
            }
            i = j + 1;
        }
        //将新链表串起来
        for (int i = 0; i < len - 1; i++) {
            list.get(i).next = list.get(i + 1);
        }
        list.get(len - 1).next = null;
        return list.get(0);
    }

    void reverse(List<ListNode> list, int start, int end) {
        while (start < end) {
            ListNode tmp = list.get(start);
            list.set(start++, list.get(end));
            list.set(end--, tmp);
        }
    }

    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        ListNode temp = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            temp = slow;
            slow = slow.next;
        }
        temp.next = temp.next.next;
        return head;
    }

    public ListNode mergeNodes(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int sum = 0;
        while (head != null) {
            if (head.val != 0) {
                sum += head.val;
            } else if (sum != 0) {
                cur.next = new ListNode(sum);
                cur = cur.next;
                sum = 0;
            }
            head = head.next;
        }
        return dummy.next;
    }

    public ListNode deleteNode(ListNode head, int val) {
            if (head == null) return head;
            if (head.val == val && head.next != null) return head.next;
            ListNode cur = head;
            ListNode pre = null;
            while (cur.val != val) {
                pre = cur;
                cur = cur.next;
            }
            pre.next = pre.next.next;
            return head;
    }


}


