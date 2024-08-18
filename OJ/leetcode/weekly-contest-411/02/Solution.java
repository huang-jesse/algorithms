class Solution {
    private static final long INF = (long)(1e10) + 1;
    public long maxEnergyBoost(int[] energyDrinkA, int[] energyDrinkB) {
        int n = energyDrinkA.length;
        long[][] dp = new long[n + 2][2];
        dp[0][0] = -INF;
        dp[0][1] = -INF;
        dp[1][0] = 0;
        dp[1][1] = 0;
        for (int i = 2; i < n + 2; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][1]) + energyDrinkA[i - 2];
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0]) + energyDrinkB[i - 2];
        }
        return Math.max(dp[n + 1][0], dp[n + 1][1]);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] energyDrinkA = {4,1,1};
        int[] energyDrinkB = {1,1,3};
        System.out.println("test: " + sol.maxEnergyBoost(energyDrinkA, energyDrinkB));
    }
}