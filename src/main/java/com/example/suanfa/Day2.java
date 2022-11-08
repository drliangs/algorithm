package com.example.suanfa;

import java.util.*;

public class Day2 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < (n - 2); i++) {
            int left = i + 1;
            int right = n - 1;
            int k = nums[i];
            while (left < right) {
                int sum = k + nums[left] + nums[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(k);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    if (!result.contains(temp)) result.add(temp);
                    left++;
                    right--;
                }

            }
        }


        return result;
    }


    public static List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return list;
        Map<String, List<String>> map = new HashMap<>();


        list.add("");
        map.put("2", Arrays.asList("a", "b", "c"));
        map.put("3", Arrays.asList("d", "e", "f"));
        map.put("4", Arrays.asList("g", "h", "i"));
        map.put("5", Arrays.asList("j", "k", "l"));
        map.put("6", Arrays.asList("m", "n", "o"));
        map.put("7", Arrays.asList("p", "q", "r", "s"));
        map.put("8", Arrays.asList("t", "u", "v"));
        map.put("9", Arrays.asList("w", "x", "y", "z"));
        return letterCombinations(digits, list, map);

    }

    //2 3
    private static List<String> letterCombinations(String digits, List<String> list, Map<String, List<String>> map) {
        if (digits.isEmpty()) {
            return list;
        }
        String s = String.valueOf(digits.charAt(0));
        List<String> strings = map.get(s);   //def
        List<String> collect = new ArrayList<>();
        strings.forEach(temp -> {
            list.forEach(s2 -> {
                System.out.println(s2 + "================" + temp);
                collect.add(s2 + temp);
            });
        });

        List<String> strings1 = letterCombinations(digits.substring(1), collect, map);
        return strings1;
    }


    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0, j = height.length - 1; i < j; ) {
            int minHeight = height[i] < height[j] ? height[i++] : height[j--];
            max = Math.max(max, (j - i + 1) * minHeight);
        }
        return max;
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
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }


    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;

    }


    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    public boolean canJump(int[] nums) {
        int n = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] >= n) n = 1;
            else n++;
            if (i == 0 && n > 1) return false;
        }
        return true;
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


    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            while (size > 0) {
                TreeNode poll = queue.poll();
                assert poll != null;
                temp.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
                size--;
            }
            result.add(temp);
        }
        return result;

    }

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                //对于第i个节点，需要考虑1作为根节点直到i作为根节点的情况，所以需要累加
                //一共i个节点，对于根节点j时,左子树的节点个数为j-1，右子树的节点个数为i-j
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
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


    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1), prev = dummy;
        dummy.next = head;
        while (true) {
            // 检查剩余节点是否有k个，不足则返回
            ListNode last = prev;
            for (int i = 0; i < k; i++) {
                last = last.next;
                if (last == null) {
                    return dummy.next;
                }
            }

            // 翻转k个节点
            ListNode curr = prev.next, next;
            for (int i = 0; i < k - 1; i++) {
                next = curr.next;
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }
            prev = curr;
        }
    }

    public boolean isPowerOfTwo(int n) {
        return (n > 0) && (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(node);
        System.out.println(rotateRight(node, 2));

    }


    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;
        ListNode cursor = head;
        ListNode tail = null;
        int length = 1;
        while (cursor.next != null) {
            cursor = cursor.next;
            length++;
        }
        int loop = length - (k % length);//得到循环的次数
        tail = cursor;
        cursor.next = head;
        cursor = head;
        for (int i = 0; i < loop; i++) {
            cursor = cursor.next;
            tail = tail.next;
        }
        tail.next = null;
        return cursor;

    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        boolean flag = false;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
                flag = true;
            }
            if (flag) {
                pre.next = cur.next;
                flag = false;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;

    }

    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        boolean flag = false;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
                flag = true;
            }
            if (flag) {
                pre.next = cur;
                pre = cur;
                flag = false;
            } else {

                pre = cur;

            }


            cur = cur.next;
        }
        return dummy.next;
    }

    //112
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) return head;
        if (head.val == head.next.val) {
            head = deleteDuplicates2(head.next);
        } else {
            head.next = deleteDuplicates2(head.next);
        }
        return head;

    }

    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null || head.next == null) return head;
        head.next = deleteDuplicates3(head.next);
        if (head.val == head.next.val) head = head.next;
        return head;
    }


    public ListNode deleteDuplicates4(ListNode head) {

        if (head == null || head.next == null) return head;
        if (head.val == head.next.val) {
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            head = deleteDuplicates4(head.next);
        } else {
            head.next = deleteDuplicates4(head.next);

        }
        return head;
    }


    //head = [1,4,3,2,5,2], x = 3
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode dummyHead1 = new ListNode(0);
        ListNode dummyHead2 = new ListNode(0);
        ListNode node1 = dummyHead1;
        ListNode node2 = dummyHead2;
        while (head != null) {
            if (head.val < x) {
                node1.next = head;
                head = head.next;

                node1 = node1.next;
            } else {
                node2.next = head;
                head = head.next;
                node2 = node2.next;
                node2.next = null;
            }
        }
        node1.next = dummyHead2.next;

        return dummyHead1.next;
    }

    //       prv    head   next  next.next
    //：head = [1,   2,     3   ,4          ,5], left = 2, right = 4
    //  head= [1,3,2,4]
    //prv head  next next.next
    //：head = [1,   3,     2   ,4         ,5], left = 2, right = 4
    //prev  head    next  next.next
    //1------->2---->3---->4


    //prev      head  next  next.next
    //1---->3----->2---->4---->5
    //4->3


    //输出：[1,4,3,2,5
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }
        head = prev.next;
        if (head == null) {
            return dummy.next;
        }

        for (int i = left; i < right; i++) {
            ListNode next = head.next;
            head.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }

        return dummy.next;
    }





}
