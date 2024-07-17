class Solution {
    private static final int INF = 0x3fffffff;
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            dp[i][0] = -INF;
            dp[i][1] = -INF;
        }
        int c = 1; // 冷冻期
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        // 预处理冷冻期
        for (int i = 1; i <= Math.min(n - 1, c); i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        // dp
        for (int i = c + 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - (c + 1)][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] prices = {1,2,3,0,2};
        int[] prices = {1,2,4};
        System.out.println("test: " + sol.maxProfit(prices));
    }
}