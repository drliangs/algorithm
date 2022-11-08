package com.example.suanfa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day8 {
    public int numIslands(char[][] grid) {
        int islandNum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    infect(grid, i, j);  // j是横向 i是竖起 i=0 j=0
                    islandNum++;
                }
            }
        }
        return islandNum;
    }

    private void infect(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = '2';
        infect(grid, i + 1, j);
        infect(grid, i - 1, j);
        infect(grid, i, j + 1);
        infect(grid, i, j - 1);
    }


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, res, 0, new ArrayList<Integer>());
        return res;
    }

    private void backtrack(int[] candidates, int target, List<List<Integer>> res, int start, ArrayList<Integer> tem_list) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(tem_list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            tem_list.add(candidates[i]);
            backtrack(candidates, target - candidates[i], res, i, tem_list);
            tem_list.remove(tem_list.size() - 1);


        }
    }


    /**
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0] == null || grid[0].length < 1) {
            return 0;
        }


        int row = grid.length;
        int col = grid[row - 1].length;
        int dp[][] = new int[row][col];


        dp[0][0] = grid[0][0];

        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[row - 1][col - 1];


    }

    //coins = [1, 2, 5], amount = 11
    //输出：3
    public int coinChange(int[] coins, int amount) {           //dp[12,12,12,12,,12,12,12,12]
        if(amount == 0) return 0;                              // 1 1,1,1,1,1,1,1,,,1,1,1,1,
        int[] dp = new int[amount+1];                          //
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for(int coin:coins){
            for(int i = coin; i <= amount; i++){
                dp[i] = Math.min(dp[i],dp[i-coin]+1);
            }
        }

        return dp[amount]==amount+1?-1:dp[amount];
    }


}
