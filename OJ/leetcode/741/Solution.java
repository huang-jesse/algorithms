import java.util.Arrays;

class Solution {
    private static final int INF = Integer.MIN_VALUE;
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[2 * n + 1][n + 1][n + 1];
        for (int i = 0; i <= 2 * n; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        dp[2][1][1] = grid[0][0];
        for (int k = 3; k <= 2 * n; k++) {
            for (int i1 = 1; i1 <= n; i1++) {
                for (int i2 = 1; i2 <= n; i2++) {
                    int j1 = k - i1, j2 = k - i2;
                    if (j1 <= 0 || j1 > n || j2 <= 0 || j2 > n) continue;
                    int currentA = grid[i1 - 1][j1 - 1], currentB = grid[i2 - 1][j2 -1];
                    if (currentA == -1 || currentB == -1) continue;
                    int a = dp[k - 1][i1 - 1][i2];
                    int b = dp[k - 1][i1][i2 - 1];
                    int c = dp[k - 1][i1 - 1][i2 - 1];
                    int d = dp[k - 1][i1][i2];
                    int temp = Math.max(Math.max(a, b), Math.max(c, d));
                    temp += currentA;
                    if (i1 != i2) {
                        // do not pick one position for two times
                        temp += currentB;
                    }
                    dp[k][i1][i2] = temp;
                }
            }
        }
        return dp[2 * n][n][n] <= 0 ? 0 : dp[2 * n][n][n];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{0,1,-1},{1,0,-1},{1,1,1}};
        System.out.println("test: " + sol.cherryPickup(grid));
    }
}