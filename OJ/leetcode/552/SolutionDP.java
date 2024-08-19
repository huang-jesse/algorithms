class SolutionDP {
    private static final int MOD = (int)(1e9 + 7);
    public int checkRecord(int n) {
        int[][] dp = new int[2][3];
        dp[0][0] = 1;
        dp[1][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            int[][] temp = new int[2][3];
            for (int a = 0; a < 2; a++) {
                for (int l = 0; l < 3; l++) {
                    // p
                    temp[a][0] = (temp[a][0] + dp[a][l]) % MOD;
                    // a
                    if (a > 0) {
                        temp[a][0] = (temp[a][0] + dp[a - 1][l]) % MOD;
                    }
                }

                // l
                for (int l = 1; l < 3; l++) {
                    temp[a][l] = dp[a][l - 1];
                }
            }
            dp = temp;
        }
        int ans = 0;
        for (int a = 0; a < 2; a++) {
            for (int l = 0; l < 3; l++) {
                ans = (ans + dp[a][l]) % MOD;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionDP sol = new SolutionDP();
        int n = 2; // res: 8
        System.out.println("test: " + sol.checkRecord(n));
    }
}