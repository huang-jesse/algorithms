class Solution {
    private int[][] dirs = {{-2, -1}, {-2, 1}
                           ,{2, -1}, {2, 1}
                           ,{-1, -2}, {1, -2}
                           ,{-1, 2}, {1, 2}};
    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[n][n][k+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = 1.0;
            }
        }
        for (int p = 1; p <=k; p++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int[] dir : dirs) {
                        int nextRow = i + dir[0];
                        int nextCol = j + dir[1];
                        if (nextRow < 0 || nextRow >= n
                        || nextCol < 0 || nextCol >= n) {
                            continue;
                        }
                        dp[i][j][p] += dp[nextRow][nextCol][p-1] / 8;
                    }
                }
            }
        }
        return dp[row][column][k];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int n = 1;
        // int k = 0;
        // int row = 0;
        // int column = 0;
        int n = 3;
        int k = 2;
        int row = 0;
        int column = 0;
        System.out.println("test: " + sol.knightProbability(n, k, row, column));
    }
}