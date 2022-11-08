package com.example.suanfa.bilbil;

//https://leetcode.cn/problems/balanced-binary-tree/
public class LeetCode_110 {

    static public class TreeNode {
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


    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return help(root) != -1;
    }

    private int help(TreeNode root) {
        if (root == null) return 0;
        int left = help(root.left);
        int right = help(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;

    }


}
