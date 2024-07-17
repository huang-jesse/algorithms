import java.util.Arrays;

class Solution {
    private static final int INF = (int)1e4 + 1;
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            int temp = INF;
            for (int j = 1; j*j <= i; j++) {
                int cur = j*j;
                temp = Math.min(temp, dp[i - cur] + 1);
            }
            dp[i] = temp;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 12;
        System.out.println("test: " + sol.numSquares(n));
    }
}