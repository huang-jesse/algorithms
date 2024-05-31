class Solution {
    private static final int MOD = (int)1e9 + 7;
    private static final int[][] DIRECTIONS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private int[][] memo;
    public int countPaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        this.memo = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = (ans + backtrack(grid, i, j)) % MOD;
            }
        }
        return ans;
    }

    private int backtrack(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (this.memo[i][j] != 0) {
            return this.memo[i][j];
        }
        int res = 1;
        int current = grid[i][j];
        for (int[] direction : DIRECTIONS) {
            int nexti = i + direction[0];
            int nextj = j + direction[1];
            if (nexti < 0 || nexti >= m || nextj < 0 || nextj >= n) continue;
            if (current >= grid[nexti][nextj]) continue;
            res = (res + backtrack(grid, nexti, nextj)) % MOD;
        }
        this.memo[i][j] = res;
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{1,1},{3,4}};
        System.out.println("test: " + sol.countPaths(grid));
    }
}