class Solution {
    private static final int INF = 0x3fffffff;
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][] dp = new int[k + 1][2];
        for (int j = 0; j <= k; j++) {
            dp[j][0] = -INF;
            dp[j][1] = -INF;
        }
        // i == 0
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[0][0] = 0;
            dp[0][1] = Math.max(dp[0][1], -prices[i]);
            for (int j = k; j >= 1; j--) {
                dp[j][1] = Math.max(dp[j][1], dp[j][0] - prices[i]);
                dp[j][0] = Math.max(dp[j][0], dp[j - 1][1] + prices[i]);
            }
        }
        int ans = 0;
        for (int j = 0; j <= k; j++) {
            ans = Math.max(ans, dp[j][0]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int k = 2;
        int[] prices = {3,2,6,5,0,3};
        System.out.println("test: " + sol.maxProfit(k, prices));
    }
}