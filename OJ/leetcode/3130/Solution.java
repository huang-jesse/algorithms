class Solution {
    private static final int MOD = (int)1e9 + 7;
    public int numberOfStableArrays(int zero, int one, int limit) {
        int[][][] dp = new int[zero + 1][one + 1][2];
        for (int i = 0; i <= Math.min(limit, zero); i++) dp[i][0][0] = 1;
        for (int j = 0; j <= Math.min(limit, one); j++) dp[0][j][1] = 1;

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                dp[i][j][0] = update(dp[i - 1][j][0], dp[i - 1][j][1]);
                if (i > limit) {
                    dp[i][j][0] = update(dp[i][j][0] + MOD, -dp[i - 1 - limit][j][1]);
                }
                dp[i][j][1] = update(dp[i][j - 1][0], dp[i][j - 1][1]);
                if (j > limit) {
                    dp[i][j][1] = update(dp[i][j][1] + MOD, -dp[i][j - 1 - limit][0]);
                }
            }
        }
        return update(dp[zero][one][0], dp[zero][one][1]);
    }

    private int update(int a, int b) {
        return (a + b) % MOD;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int zero = 3;
        int one = 3;
        int limit = 2;
        System.out.println("test: " + sol.numberOfStableArrays(zero, one, limit));
    }
}