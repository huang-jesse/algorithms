class Solution {
    private static final int INF = 0x3fffffff;
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int buy = -INF;
        int sell = 0;
        for (int i = 0; i < n; i++) {
            int newSell = Math.max(sell, buy + prices[i] - fee);
            int newBuy = Math.max(buy, sell - prices[i]);
            buy = newBuy;
            sell = newSell;
        }
        return sell;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] prices = {1,3,2,8,4,9};
        int fee = 2;
        System.out.println("test: " + sol.maxProfit(prices, fee));
    }
}