class SolutionOptimization {
    static final int MOD = (int)(1e9 + 7);
    public int numberOfWays(int n, int x) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; (int)(Math.pow(i, x)) <= n; i++) {
            int currentNum = (int)(Math.pow(i, x));
            for (int j = n; j >= currentNum; j--) {
                dp[j] = (dp[j] + dp[j - currentNum]) % MOD;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int n = 10;
        int x = 2;
        System.out.println("test: " + sol.numberOfWays(n, x));
    }
}