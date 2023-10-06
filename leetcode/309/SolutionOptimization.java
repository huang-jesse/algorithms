class SolutionOptimization {
    private static final int INF = 0x3fffffff;
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // 初始值
        int buy0 = -prices[0];
        int sell0 = 0;// 在冷冻期
        int sell1 = 0;// 不在冷冻期
        // dp
        for (int i = 1; i < n; i++) {
            int newBuy0 = Math.max(buy0, sell1 - prices[i]);
            int newSell0 = buy0 + prices[i];
            int newSell1 = Math.max(sell1, sell0);
            buy0 = newBuy0;
            sell0 = newSell0;
            sell1 = newSell1;
        }
        return Math.max(sell0, sell1);
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        // int[] prices = {1,2,3,0,2};
        int[] prices = {1,2,4};
        System.out.println("test: " + sol.maxProfit(prices));
    }
}