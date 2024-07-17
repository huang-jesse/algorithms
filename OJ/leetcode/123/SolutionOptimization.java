class SolutionOptimization {
    private static final int INF = 0x3fffffff;
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0];
        int sell1 = -INF;
        int buy2 = -INF;
        int sell2 = -INF;
        for (int i = 1; i < n; i++) {
            sell2 = Math.max(sell2, buy2 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy1 = Math.max(buy1, -prices[i]);
        }
        int ans = 0;
        return Math.max(ans, Math.max(sell1, sell2));
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] prices = {1,2,3,4,5};
        // int[] prices = {3,6,0,4,1,5};
        // int[] prices = {3,3,5,0,0,3,1,4};
        System.out.println("test: " + sol.maxProfit(prices));
    }
}