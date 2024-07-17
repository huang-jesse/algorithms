import java.util.Arrays;

class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        System.arraycopy(matrix[0], 0, dp[0], 0, n);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int preMin = dp[i - 1][j];
                if (j > 0) {
                    preMin = Math.min(dp[i - 1][ j - 1], preMin);
                }
                if (j < n - 1) {
                    preMin = Math.min(dp[i - 1][ j + 1], preMin);
                }
                dp[i][j] = matrix[i][j] + preMin;
            }
        }
        return Arrays.stream(dp[n - 1]).min().getAsInt();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] matrix = {{2,1,3},{6,5,4},{7,8,9}};
        System.out.println("test: " + sol.minFallingPathSum(matrix));
    }
}