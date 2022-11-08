package com.example.suanfa;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Day3 {
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

    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.left);
        flatten(root.right);
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null) {
            root = root.right;
        }
        root.right = temp;


    }

    public void flatten1(TreeNode root) {
        solve(root);

    }

    private TreeNode solve(TreeNode root) {
        if (root == null) return null;
        TreeNode leftNode = solve(root.left);  //root  1
        TreeNode rightNode = solve(root.right);   //  2   3
        if (leftNode == null) {
            return root;
        }
        if (rightNode == null) {
            root.right = leftNode;
            root.left = null;
            return root;
        }
        TreeNode rightRecord = root.right;
        root.right = root.left;
        root.left = null;
        TreeNode temp = root.right;
        while (temp.right != null) {
            temp = temp.right;
        }
        temp.right = rightRecord;
        return root;

    }


    public void flatten3(TreeNode root) {
        Queue<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.offer(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                TreeNode node = stack.poll();
                TreeNode temp = node.right;
                node.right = node.left;
                node.left = null;
                while (node.right != null) {
                    node = node.right;
                }
                node.right = temp;
                root = temp;
            }
        }

    }

    /**
     * @param root
     * @return 1
     * 2 ----> 3
     * 4 --> 5   6  7
     */

    public Node connect(Node root) {
        if (root == null) return null;
        if (root.left != null) {
            root.left.next = root.right;

            if (root.next != null) {
                root.right.next = root.next.left;
            }
        }
        connect(root.left);
        connect(root.right);

        return root;
    }

    //                o root
    //                 / \
    //     root.left  o —— o  root.right
    //               /    / \
    //              o —— o   o
    //             /        / \
    //            o        o   o

    public Node connect1(Node root) {
        if (root == null) return null;
        if (root.left != null) {
            if (root.right != null) {
                root.left.next = root.right;
            } else {
                root.left.next = nextNode(root.next);
            }
        }
        if (root.right != null) {
            root.right.next = nextNode(root.next);
        }
        connect1(root.right);
        connect1(root.left);
        return root;
    }

    private Node nextNode(Node next) {
        while (next != null) {
            if (next.left != null) {
                return next.right;
            }
            if (next.right != null) {
                return next.right;
            }
            next = next.next;
        }
        return next;
    }


    public Node connectz(Node root) {
        Node dummy = new Node(0); // 每一层的头结点
        dummy.next = root;
        while (dummy.next != null) {
            Node cur = dummy.next;
            Node pre = dummy;
            dummy.next = null;
            while (cur != null) {
                if (cur.left != null) {
                    pre.next = cur.left;
                    pre = cur.left;
                }
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = cur.right;
                }
                cur = cur.next;
            }
        }
        return root;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" + "val=" + val + ", next=" + (next != null ? super.toString() + "    " : null) + next + +'}';
        }
    }

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                fast = head;
                while (fast != slow) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;

    }


    public void reorderList(ListNode head) {
        Deque<ListNode> queue = new LinkedList<>();
        ListNode cur = head;
        while (cur != null) {
            queue.addLast(cur);
            cur = cur.next;
        }
        while (!queue.isEmpty()) {
            if (cur == null) {
                cur = queue.pollFirst();
            } else {
                cur.next = queue.pollFirst();
                cur = cur.next;
            }
            cur.next = queue.pollLast();
            cur = cur.next;
        }
        if (cur != null) {
            cur.next = null;
        }

    }


    public void reorderListz(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode p = head;
        while (p.next.next != null) {
            p = p.next;
        }
        ListNode insertNode = p.next;
        p.next = null;
        insertNode.next = head.next;
        head.next = insertNode;
        reorderListz(insertNode.next);
    }

    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0), pre = dummy;
        dummy.next = head;
        while (head != null && head.next != null) {
            if (head.val <= head.next.val) {
                head = head.next;
                continue;
            }
            while (pre.next.val < head.next.val) {
                pre = pre.next;
            }
            ListNode next = head.next;
            head.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }

        return dummy.next;
    }

    public ListNode sortList(ListNode head) {
        return head == null ? null : megerSort(head);
    }

    private ListNode megerSort(ListNode head) {

        if (head.next != null) return head; //base
        ListNode p = head, q = head, prev = null;

        while (q != null && q.next != null) {
            prev = p;
            p = p.next;
            q = q.next.next;
        }
        prev.next = null;
        ListNode l = megerSort(head);
        ListNode r = megerSort(p);
        return meger(l, r);


    }

    private ListNode meger(ListNode l, ListNode r) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l != null && r != null) {
            if (l.val <= r.val) {
                cur.next = l;
                cur = cur.next;
                l = l.next;
            } else {
                cur.next = r;
                cur = cur.next;
                r = r.next;

            }
        }
        if (l != null) {
            cur.next = l;
        }
        if (r != null) {
            cur.next = r;
        }
        return dummy.next;
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode header = new ListNode(-1);
        header.next = header;
        ListNode prev = header;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = head.next.next;
            } else {
                prev = prev.next;
            }
        }
        return header.next;
    }

    public ListNode removeElements1(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        // 已经为null，提前退出
        if (head == null) {
            return head;
        }
        ListNode pre = head;
        ListNode next = head.next;
        while (next != null) {
            if (next.val == pre.val) {
                pre.next = next.next;
            } else {
                pre = next;
            }
            next = next.next;
        }
        return head;

    }

    //slow       //fast
    //head = [1,2,2,1] 774   908      916    923
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = reverse(slow.next);
        while (slow != null) {
            if (head.val != slow.val) {
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1, null))));
        System.out.println(listNode);
        isPalindrome(listNode);
    }

    //916 923   2 1
    private static ListNode reverse(ListNode next) {
        if (next.next == null) return next;
        ListNode newHead = reverse(next.next);
        next.next.next = next;
        next.next = null;
        return newHead;
    }

    public boolean isPalindrome1(ListNode head) {

        ListNode rear = head;
        Stack<ListNode> que = new Stack<ListNode>();
        while (rear != null) {
            que.push(rear);
            rear = rear.next;
        }
        rear = head;
        while (!que.isEmpty()) {
            if (rear.val != que.pop().val) {
                return false;
            }
            rear = rear.next;
        }
        return true;


    }

    //        o          p e
    // head = [1,        2,         3         ,4       ,5]
    //      1,3,5,2,4]
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode o = head;
        ListNode p = head.next;
        ListNode e = p;
        while (o.next != null && e.next != null) {
            o.next = e.next;
            o = o.next;
            e.next = o.next;
            e = e.next;
        }
        o.next = p;
        return head;

    }

    Node last = null;




}

