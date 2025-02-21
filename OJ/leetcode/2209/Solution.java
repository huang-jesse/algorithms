class Solution {
    private static final char ZERO = '0';
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int n = floor.length();
        char[] s = floor.toCharArray();
        int[][] dp = new int[numCarpets + 1][n];
        dp[0][0] = s[0] - ZERO;
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + (s[j] - ZERO);
        }

        for (int i = 1; i <= numCarpets; i++) {
            for (int j = carpetLen * i; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1] + (s[j] - ZERO), dp[i - 1][j - carpetLen]);
            }
        }
        return dp[numCarpets][n - 1];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String floor = "10110101";
        int numcarpets = 2;
        int carpetLen = 2; // ans = 2
        System.out.println("test: " + sol.minimumWhiteTiles(floor, numcarpets, carpetLen));
    }
}