class Solution {
    private Integer[][][] memo;
    private int k;
    private static final int MOD = (int)1e9 + 7;
    public int numberOfPaths(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        this.memo = new Integer[n][m][k + 1];
        this.k = k;
        return backtrack(grid, 0, 0, 0);
    }

    private int backtrack(int[][] grid, int row, int col, int curK) {
        int n = grid.length;
        int m = grid[0].length;
        curK = (curK + grid[row][col]) % k;
        if (row == n - 1 && col == m - 1) {
            if (curK % k == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        if (memo[row][col][curK] != null) {
            return memo[row][col][curK];
        }
        int res = 0;
        if (row < n - 1) {
            res = (res + backtrack(grid, row + 1, col, curK)) % MOD;
        }
        if (col < m - 1) {
            res = (res + backtrack(grid, row, col + 1, curK)) % MOD;
        }
        memo[row][col][curK] = res ;
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{1,5,3}};
        int k = 9;
        // int[][] grid = {{1,5,3,7,3,2,3,5}};
        // int k = 29;
        // int[][] grid = {{7,3,4,9},{2,3,6,2},{2,3,7,0}};
        // int k = 1;
        // int[][] grid = {{5,2,4},{3,0,5},{0,7,2}};
        // int k = 3;
        System.out.println("test: " + sol.numberOfPaths(grid, k));
    }
}