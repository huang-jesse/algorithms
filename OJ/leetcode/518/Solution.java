class Solution {
    public int change(int amount, int[] coins) {
        // dp[i] representing the number of combinations that make up that amount of i
        int[] dp = new int[amount+1];
        // initial
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int amount = 5;
        int[] coins = {1,2,5};
        System.out.println("test: " + sol.change(amount, coins));
    }
}