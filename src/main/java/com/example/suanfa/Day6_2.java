package com.example.suanfa;

public class Day6_2 {
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        dfs(root);
        cur.right = head;
        head.left = cur;
        return head;
    }

    volatile Node head;
    Node cur;

    private void dfs(Node root) {
        if (root == null) return;
        dfs(root.left);
        if (head == null) {
            head = root;
            cur = head;
        } else {
            cur.right = root;
            root.left = cur;
            cur = root;
        }
        dfs(root.right);

    }

    static public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode fast = headA;
        ListNode slow = headB;
        while (fast != slow) {
            fast = fast == null ? headB : fast.next;
            slow = slow == null ? headA : slow.next;
        }
        return fast;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = head, latter = head;
        for (int i = 0; i < n; i++) {
            latter = latter.next;
        }
        if (latter == null) return head;
        while (latter != null) {
            latter = latter.next;
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return head;

    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head, vhead = head;
        while (fast != null && fast.next != null) {
            vhead = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        vhead.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        return merge(l1, l2);


    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode t = new ListNode(-1);
        ListNode p = t;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;

        }
        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return t.next;
    }

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    volatile TreeNode f;

    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) return root;
        f = new TreeNode(-1);
        TreeNode prv = f;
        dfsz(root);
        return prv.right;
    }

    private void dfsz(TreeNode root) {
        if (root == null) return;
        dfsz(root.left);
        f.right = root;
        root.left = null;
        f = f.right;
        dfsz(root.right);

    }

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }


        ListNode pre = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }
        slow = head;
        while (pre != null) {
            if (pre.val != slow.val) {
                return false;
            }
            slow = slow.next;
            pre = pre.next;
        }
        return true;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int carry = 0;
        ListNode ans = new ListNode(0);
        ListNode cur = ans;
        while (l1 != null || l2 != null) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;


            int num = num1 + num2 + carry;
            carry = num / 10;
            cur.next = new ListNode(num % 10);
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            cur = cur.next;
        }
        if (carry == 1) cur.next = new ListNode(carry);
        return ans.next;

    }

    public ListNode partition(ListNode head, int x) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode p = dummyHead;
        while (p.next != null && p.next.val < x) {
            p = p.next;
        }
        ListNode q = p.next;
        if (q == null) {
            return head;
        }
        while (q.next != null) {
            if (q.next.val >= x) {
                q = q.next;
            } else {
                ListNode cur = q.next;
                q.next = q.next.next;
                ListNode temp = p.next;
                p.next = cur;
                cur.next = temp;
            }
        }
        return dummyHead.next;


    }


    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while (pre.next != null) {
            ListNode cur = pre.next;
            while (cur.next != null) {
                if (pre.next.val == cur.next.val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            pre = pre.next;

        }
        return dummyHead.next;
    }


}