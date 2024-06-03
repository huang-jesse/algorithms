import java.util.Arrays;

class Solution {
    private static final int MOD = (int)1e9 + 7;
    private int limit;
    private int[][][][] memo;
    public int numberOfStableArrays(int zero, int one, int limit) {
        this.limit = limit;
        this.memo = new int[zero + 1][one + 1][2][limit + 1];
        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++) {
                Arrays.fill(memo[i][j][0], -1);
                Arrays.fill(memo[i][j][1], -1);
            }
        }
        return backtrack(zero, one, 0, 0);
    }

    private int backtrack(int zero, int one, int flag, int continous) {
        if (zero == 0 && one == 0) return 1;
        int min = Math.min(zero, one);
        int max = Math.max(zero, one);
        if (max - limit * min > limit) return 0; // there are not stable
        if (this.memo[zero][one][flag][continous] != -1) {
            return this.memo[zero][one][flag][continous];
        }
        int res = 0;
        if ((zero > 0) && (flag != 0 || (flag == 0 && continous < limit))) {
            res = backtrack(zero - 1, one, 0, flag == 0 ? continous + 1 : 1);
        }
        if ((one > 0) && (flag != 1 || (flag == 1 && continous < limit))) {
            res = (res + backtrack(zero, one - 1, 1, flag == 1 ? continous + 1 : 1)) % MOD;
        }
        this.memo[zero][one][flag][continous] = res;
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int zero = 3;
        int one = 3;
        int limit = 2;
        System.out.println("test: " + sol.numberOfStableArrays(zero, one, limit));
    }
}