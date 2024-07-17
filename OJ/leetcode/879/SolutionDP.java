class SolutionDP {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        final int MOD = (int)1e9 + 7;
        int len = group.length;
        int[][][] dp = new int[len+1][n+1][minProfit+1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= len; i++) {
            int members = group[i-1];
            int earn = profit[i-1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    if (members > j) {
                        dp[i][j][k] = dp[i-1][j][k];
                    } else {
                        dp[i][j][k] = (dp[i-1][j][k] + dp[i-1][j - members][Math.max(0, k - earn)]) % MOD;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            ans = (ans + dp[len][i][minProfit]) % MOD;
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionDP sol = new SolutionDP();
        int n = 5;
        int minProfit = 3;
        int[] group = {2,2};
        int[] profit = {2,3};

        System.out.println("test: " + sol.profitableSchemes(n, minProfit, group, profit));
    }
}