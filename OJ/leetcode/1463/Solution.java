import java.util.Arrays;

class Solution {
    private static final int INF = Integer.MIN_VALUE;
    private static final int[][] COMBINATIONS = {{-1, -1}, {0, -1}, {1, -1},
                                                 {-1, 0}, {0, 0}, {1, 0},
                                                 {-1, 1}, {0, 1}, {1, 1}};
    public int cherryPickup(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m + 1][n + 2][n + 2];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j < n + 2; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        dp[1][1][n] = grid[0][0] + grid[0][n - 1];
        for (int k = 2; k <= m; k++) {
            for (int j1 = 1; j1 <= Math.min(k, n); j1++) {
                for (int j2 = n; j2 >= Math.max(n - k + 1, j1 + 1); j2--) {
                    // all combinations
                    for (int[] combination : COMBINATIONS) {
                        dp[k][j1][j2] = Math.max(dp[k][j1][j2], dp[k - 1][j1 + combination[0]][j2 + combination[1]]);
                    }
                    // add current cell
                    dp[k][j1][j2] += grid[k - 1][j1 - 1] + grid[k - 1][j2 - 1];
                }
            }
        }
        int ans = 0;
        for (int j1 = 1; j1 <= n; j1++) {
            for (int j2 = n; j2 >= 1; j2--) {
                ans = Math.max(ans, dp[m][j1][j2]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{1,0,0,0,0,0,1},{2,0,0,0,0,3,0},{2,0,9,0,0,0,0},{0,3,0,5,4,0,0},{1,0,2,3,0,0,6}};
        System.out.println("test: " + sol.cherryPickup(grid));
    }
}