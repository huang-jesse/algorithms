class SolutionDP {
    public int findIntegers(int n) {
        int[][] dp = new int[32][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < 32; i++) {
            dp[i][1] = dp[i - 1][0];
            dp[i][0] = dp[i - 1][1] + dp[i - 1][0];
        }

        int ans = 0;
        int preBit = 0;
        for (int i = 31; i >= 0; i--) {
            int curBit = (n >> i) & 1;
            if (curBit == 1) {
                ans += dp[i][0];
            }
            if (preBit == 1 && curBit == 1) break;
            if (i == 0) ans++;
            preBit = curBit;
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionDP sol = new SolutionDP();
        int n = 5;
        System.out.println("test: " + sol.findIntegers(n));
    }
}