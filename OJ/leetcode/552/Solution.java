import java.util.Arrays;

class Solution {
    private static final int MOD = (int)(1e9 + 7);
    private int n;
    private int[][][] memo;
    public int checkRecord(int n) {
        this.n = n;
        this.memo = new int[n][2][3];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i][0], -1);
            Arrays.fill(memo[i][1], -1);
        }
        return backtrack(0, 0, 0);
    }

    private int backtrack(int i, int aCount, int lCount) {
        if (i == n) return 1;
        if (this.memo[i][aCount][lCount] != -1) return this.memo[i][aCount][lCount];

        int res = 0;
        if (aCount < 1) {
            res = (res + backtrack(i + 1, aCount + 1, 0)) % MOD;
        }
        if (lCount < 2) {
            res = (res + backtrack(i + 1, aCount, lCount + 1)) % MOD;
        }
        res = (res + backtrack(i + 1, aCount, 0)) % MOD;

        this.memo[i][aCount][lCount] = res;
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 2; // res: 8
        System.out.println("test: " + sol.checkRecord(n));
    }
}