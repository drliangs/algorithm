package com.example.suanfa;

import java.util.*;

public class Day9 {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int item : nums1) {
            set1.add(item);
        }
        for (int item : nums2) {
            if (set1.contains(item)) {
                set2.add(item);
            }
        }
        int[] result = new int[set2.size()];
        int start = 0;
        for (int item : set2) {
            result[start++] = item;
        }
        return result;

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

    public List<Integer> inorderTreeNode(TreeNode treeNode) {
        if (treeNode == null) return null;
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            TreeNode left = stack.pop();
            result.add(left.val);
            treeNode = left.right;
        }
        return result;
    }


}
