class SolutionOptimization {
    private static final int MOD = (int)1e9 + 7;
    private int limit;
    private int[][][] memo;
    public int numberOfStableArrays(int zero, int one, int limit) {
        this.limit = limit;
        this.memo = new int[zero + 1][one + 1][2];
        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++) {
                this.memo[i][j][0] = -1;
                this.memo[i][j][1] = -1;
            }
        }
        return (backtrack(zero, one, 0) + backtrack(zero, one, 1)) % MOD;
    }

    private int backtrack(int i, int j, int k) {
        if (i == 0) {
            return k == 1 && j <= limit ? 1 : 0;
        }
        if (j == 0) {
            return k == 0 && i <= limit ? 1 : 0;
        }
        if (this.memo[i][j][k] != -1) {
            return this.memo[i][j][k];
        }
        int res = 0;
        if (k == 0) {
            res = (backtrack(i - 1, j, 0) + backtrack(i - 1, j, 1)) % MOD;
            if (i - 1 - limit >= 0) {
                // [i-1-limit, i-1] continous zero of limit times
                res = (res + MOD - backtrack(i - 1 - limit, j, 1)) % MOD;
            }
        } else {
            // k == 1
            res = (backtrack(i, j - 1, 0) + backtrack(i, j - 1, 1)) % MOD;
            if (j - 1 - limit >= 0) {
                // [j-1-limit, j-1]  continous one of limit times
                res = (res + MOD - backtrack(i, j - 1 - limit, 0)) % MOD;
            }
        }
        this.memo[i][j][k] = res;
        return res;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int zero = 3;
        int one = 3;
        int limit = 2;
        System.out.println("test: " + sol.numberOfStableArrays(zero, one, limit));
    }
}