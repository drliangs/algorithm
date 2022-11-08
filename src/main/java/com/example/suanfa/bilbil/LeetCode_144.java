package com.example.suanfa.bilbil;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.cn/problems/binary-tree-preorder-traversal/
public class LeetCode_144 {
    public class TreeNode {
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

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                ans.add(root.val);
                stack.push(root);
                root = root.left;
            }
            TreeNode stackNode = stack.pop();
            root = stackNode.right;
        }
        return ans;
    }

    public static void main(String[] args) {
        int a = 0x45;  //69
        int b = 0xf0;  //240
        
        System.out.println(a | b);
    }
}
