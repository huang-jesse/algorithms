class Solution {
    public int maxSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                int current = grid[i - 1][j - 1] + grid[i - 1][j] + grid[i - 1][j + 1] + grid[i][j]
                    + grid[i + 1][j - 1] + grid[i + 1][j] + grid[i + 1][j + 1];
                ans = Math.max(ans, current);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{6,2,1,3},{4,2,1,5},{9,2,8,7},{4,1,2,9}};
        System.out.println("test: " + sol.maxSum(grid));
    }
}