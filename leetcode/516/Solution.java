class Solution {
    public int longestPalindromeSubseq(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = n-2; i >= 0; i--) {
            for (int j = i+1; j < n; j++) {
                if (arr[i] == arr[j]) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    // arr[i] != arr[j]
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "bbbab";
        System.out.println("test: " + sol.longestPalindromeSubseq(s));
    }
}