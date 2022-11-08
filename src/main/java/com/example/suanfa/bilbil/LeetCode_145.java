package com.example.suanfa.bilbil;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.cn/problems/binary-tree-postorder-traversal/
public class LeetCode_145 {


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

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return null;
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prevAccess = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prevAccess) {
                ans.add(root.val);
                prevAccess = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return ans;
    }


}
