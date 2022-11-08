package com.example.suanfa;

import java.util.Arrays;

public class day10 {

    ///输入：haystack = "sadbutsad", needle = "sad"
    //输出：0
    //解释："sad" 在下标 0 和 6 处匹配。
    //第一个匹配项的下标是 0 ，所以返回 0 。
    //haystack = "leetcode", needle = "leeto"
    public int strStr(String haystack, String needle) {
        if (needle == "") return 0;
        int hi = 0, ni = 0;
        while (hi < haystack.length() && ni < needle.length()) {
            char hc = haystack.charAt(hi);
            char nc = needle.charAt(ni);
            if (hc == nc) {
                hi++;
                ni++;
            } else {
                hi = hi - ni + 1;
                ni = 0;
            }
            if (ni == needle.length()) {
                return hi - ni;
            }
        }
        return -1;

    }

    /**
     * 输入：nums = [-1,2,1,-4], target = 1
     * 输出：2
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) return sum;
                if (Math.abs(sum - target) < diff) {
                    diff = Math.abs(sum - target);
                    res = sum;
                }
                if (sum < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return res;
    }

    public int findRepeatNumber(int[] nums) {
        int[] ans = new int[nums.length];
        int num = ans[0];
        for (int i = 0; i < nums.length; i++) {
            num = nums[i];
            ans[num]++;
            if (ans[num] > 1) {
                return num;
            }
        }
        return 0;
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            if (matrix[row][col] > target) {
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                return true;
            }

        }
        return false;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int[] preOrder, inOrder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.inOrder = inorder;
        this.preOrder = preorder;
        return dfs(0, preorder.length - 1, 0, inorder.length - 1);
    }

    //preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    private TreeNode dfs(int preS, int preE, int inS, int inE) {
        TreeNode root = new TreeNode(preOrder[preS]);
        for (int i = inS; i < inE; i++) {
            if (preOrder[preS] == inOrder[i]) {
                root.left = dfs(preS + 1, preS + i - inS, inS, i - 1);
                root.right = dfs(preS + i - inS + 1, preE, i + 1, inE);
                break;
            }
        }
        return root;
    }
}
