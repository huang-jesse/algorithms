class SolutionNew {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int pre = Integer.MAX_VALUE;
        for (int price : prices) {
            if (pre < price) {
                profit += (price - pre);
            }
            pre = price;
        }
        return profit;
    }

    public static void main(String[] args) {
        SolutionNew sol = new SolutionNew();
        int[] prices = {7,1,5,3,6,4};
        System.out.println("test: " + sol.maxProfit(prices));
    }
}