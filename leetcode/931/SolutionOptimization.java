import java.util.Arrays;

class SolutionOptimization {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[] dp = new int[n];
        System.arraycopy(matrix[0], 0, dp, 0, n);
        for (int i = 1; i < n; i++) {
            int preMin = dp[0];
            for (int j = 0; j < n; j++) {
                int temp = preMin;
                preMin = dp[j];
                if (j > 0) {
                    temp = Math.min(preMin, temp);
                }
                if (j < n - 1) {
                    temp = Math.min(dp[j + 1], temp);
                }
                dp[j] = matrix[i][j] + temp;
            }
        }
        return Arrays.stream(dp).min().getAsInt();
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[][] matrix = {{2,1,3},{6,5,4},{7,8,9}};
        System.out.println("test: " + sol.minFallingPathSum(matrix));
    }
}