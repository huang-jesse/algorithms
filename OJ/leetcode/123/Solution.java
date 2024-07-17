import java.util.Arrays;

class Solution {
    private static final int INF = 0x3fffffff;
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // {index, transactionTimes, isBuyStock: 1 yes, 0 no}
        int[][][] dp = new int[n][3][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                Arrays.fill(dp[i][j], -INF);
            }
        }
        dp[0][0][0] = 0;
        dp[0][0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Math.max(dp[i - 1][0][1], -prices[i]);
            for (int j = 1; j < 3; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0] - prices[i]);
            }
        }
        int ans = 0;
        for (int j = 0; j < 3; j++) {
            ans = Math.max(ans, dp[n - 1][j][0]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] prices = {1,2,3,4,5};
        // int[] prices = {3,6,0,4,1,5};
        // int[] prices = {3,3,5,0,0,3,1,4};
        System.out.println("test: " + sol.maxProfit(prices));
    }
}