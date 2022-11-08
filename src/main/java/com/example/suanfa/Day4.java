package com.example.suanfa;

import java.util.*;

public class Day4 {
    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }



    //love
    public Node flatten(Node head) {
        if (head == null) return null;
        getTail(head);
        return head;
    }

    private Node getTail(Node head) {
        Node pre = head, p = head;
        while (p != null) {
            pre = p;
            p = p.next;
            if (pre.child != null) {
                pre.next = pre.child;
                pre.child.prev = pre;
                pre.child = null;
                Node tail = getTail(pre.next);
                if (p != null) {
                    tail.next = p;
                    p.prev = tail;
                } else {
                    return tail;
                }
            }

        }
        return pre;
    }


    private static class ListNode {

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

    //给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
    //你可以假设除了数字 0 之外，这两个数字都不会以零开头。
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode h1 = l1;
        ListNode h2 = l2;
        ListNode cur = result;
        while (h1 != null && h2 != null) {
            h1 = h1.next;
            h2 = h2.next;
            ListNode temp = new ListNode(h1.val + h2.val);
            if ((h1.val + h2.val) > 10) {
                temp = h1.val > h2.val ? h1 : h2;
            }
            cur.next = temp;
            cur = cur.next;

        }
        if (h1 != null) {
            cur.next = h1;
        }
        if (h2 != null) {
            cur.next = h2;
        }
        return result.next;


    }

    /**
     * head = [1,2,3,4,5,6,7,8,9,10], k = 3
     * 输出：[[1,2,3,4],[5,6,7],[8,9,10]]
     * 解释：
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode head, int k) {
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }
        int a = len / k, b = len % k;
        p = head;
        ListNode[] ans = new ListNode[k];
        for (int i = 0; i < k; i++) {
            ans[i] = p;
            if (p == null) continue;
            ListNode temp = p;
            for (int j = 1; j < a + (b > 0 ? 1 : 0); j++) {
                temp = temp.next;
            }
            p = temp.next;
            temp.next = null;
            b--;
        }
        return ans;
    }

    public int numComponents(ListNode head, int[] nums) {
        boolean[] v = new boolean[10005];
        for (int i = 0; i < nums.length; i++) {
            v[nums[i]] = true;
        }
        int ans = 0;
        while (head != null) {
            if (v[head.val]) {
                while (head.next != null && v[head.next.val]) {
                    head = head.next;
                }
                ans++;

            }
            head = head.next;
        }
        return ans;
    }

    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public int[] nextLargerNodes(ListNode head) {
        if (head == null) return null;
        ArrayList<Integer> list = new ArrayList<>();
        int size = 0;
        while (head != null) {
            list.add(head.val);
            size++;
            head = head.next;
        }
        //输入：head = [2,7,4,3,5]
        //输出：[7,0,5,5,0]
        int[] ans = new int[size];
        //53472
        Stack<Integer> stack = new Stack<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            while (!stack.empty() && stack.peek() <= list.get(i)) {
                stack.pop();
            }
            ans[i] = stack.empty() ? 0 : stack.peek();
            stack.push(list.get(i));
        }
        return ans;
    }

    //思路：如果中间的数小于最右边的数，则右半段是有序的，
    // 若中间数大于最右边数，则左半段是有序的，
    // 我们只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，这样就可以确定保留哪半边了
    public static int search(int[] nums, int target) {
        //：nums = [4,5,6,7,0,1,2], target = 0
        int length = nums.length;
        int left = 0, right = length - 1;
        while (left <= right) {
            int middle = (left + right) >> 1;
            if (nums[middle] == target) return middle;
            else if (nums[middle] < nums[right]) {
                if (nums[middle] < target && target <= nums[right]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            } else {
                if (nums[left] <= target && target < nums[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
        }
        return -1;


    }

    //输入：[4,3,2,0,1,1,5]
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int water = 0;
        if (height.length < 3) {
            return 0;
        }
        for (int i = 0; i < height.length; i++) {

            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int popnum = stack.pop(); //3
                while (!stack.isEmpty() && height[popnum] == height[stack.peek()]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    int temp = height[stack.peek()]; //2
                    int hig = Math.min(temp - height[popnum], height[i] - height[popnum]);
                    //宽
                    int wid = i - stack.peek() - 1;
                    water += hig * wid;
                }


            }

            stack.push(i); //3210


        }
        return water;

    }

    public int trap1(int[] height) {
        int res = 0, left = 0, letMax = 0, right = height.length - 1, rightMax = 0;
        while (left <= right) {
            if (letMax <= rightMax) {
                letMax = Math.max(letMax, height[left]);
                res += letMax - height[left++];
            } else {
                rightMax = Math.max(rightMax, height[right]);
                res += rightMax - height[right--];
            }
        }
        return res;

    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];

    }


    /**
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * https://leetcode.cn/problems/longest-increasing-subsequence/
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int j : dp) {
            res = Math.max(res, j);
        }
        return res;


    }

    public int lengthOfLISz(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);

        }
        return max;

    }


    //给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
    //请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(d -> d));
        for (int num : nums) {
            queue.add(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.poll();
    }

    public void rotate(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len / 2; i++) {
            int start = i;
            int end = len - i - 1;
            for (int j = 0; j < end - start; j++) {
                int temp = matrix[start][start + j];
                matrix[start][start + j] = matrix[end - j][start];
                matrix[end - j][start] = matrix[end][end - j];
                matrix[end][end - j] = matrix[start + j][end];
                matrix[start + j][end] = temp;
            }
        }

    }


    public static void main(String[] args) {
        int search = search(new int[]{4, 5, 6, 7, 1, 9, 10}, 1);
        System.out.println(search);
    }


}
