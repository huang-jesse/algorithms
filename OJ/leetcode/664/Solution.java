class Solution {
    public int strangePrinter(String s) {
        int n = s.length();
        // answer is dp[0][n-1]
        int[][] dp = new int[n][n];
        // dp[i][i] = 1
        // i&j means print range
        // when s[i] == s[j], dp[i][j] = dp[i][j-1]
        // when s[i] != s[j], dp[i][j] = min(dp[i][k] + dp[k+1][j])(i <= k < j)
        for (int i = n-1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j-1];
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        min = Math.min(min, dp[i][k] + dp[k+1][j]);
                    }
                    dp[i][j] = min;
                }
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "aba";
        System.out.println("test: " + sol.strangePrinter(s));
    }
}