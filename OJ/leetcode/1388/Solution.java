import java.util.Arrays;

class Solution {
    private final static int INF = 500000;
    public int maxSizeSlices(int[] slices) {
        int[] v1 = new int[slices.length - 1];
        int[] v2 = new int[slices.length - 1];
        System.arraycopy(slices, 1, v1, 0, slices.length - 1);
        System.arraycopy(slices, 0, v2, 0, slices.length - 1);
        return Math.max(calculate(v1), calculate(v2));
    }

    private int calculate(int[] slices) {
        int n = slices.length;
        int m = (n + 1) / 3;
        int[][] dp = new int[n][m + 1];
        // i < 2, j >= 2时，非法
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -INF);
        }
        dp[0][0] = 0;
        dp[0][1] = slices[0];
        dp[1][0] = 0;
        dp[1][1] = Math.max(slices[0], slices[1]);
        for (int i = 2; i < n; i++) {
            dp[i][0] = 0;
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(dp[i - 2][j - 1] + slices[i], dp[i - 1][j]);
            }
        }
        return dp[n - 1][m];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] slices = {1,2,3,4,5,6};
        System.out.println("test: " + sol.maxSizeSlices(slices));
    }
}