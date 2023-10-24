class Solution {
    private static final int MOD = (int)(1e9 + 7);
    public int numRollsToTarget(int n, int k, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = target; j >= 0; j--) {
                dp[j] = 0;
                for (int l = 1; l <= Math.min(k, j); l++) {
                    dp[j] = (dp[j] + dp[j - l]) % MOD;
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 30;
        int k = 30;
        int target = 500; // 222616187
        // int n = 2;
        // int k = 6;
        // int target = 7;
        System.out.println("test: " + sol.numRollsToTarget(n, k, target));
    }
}