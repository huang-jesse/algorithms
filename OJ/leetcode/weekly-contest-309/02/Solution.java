class Solution {
    private int[][] memo;
    private final static int MOD = (int)(1e9 + 7);
    public int numberOfWays(int startPos, int endPos, int k) {
        int distance = Math.abs(endPos - startPos);
        if (k < distance || (k - distance) % 2 != 0) {
            return 0;
        }
        this.memo = new int[3001][k + 1];
        return backtrack(startPos, endPos, k);
    }

    private int backtrack(int startPos, int endPos, int k) {
        if (k == 0) {
            return 1;
        }
        if (memo[startPos + 1000][k] != 0) {
            return memo[startPos + 1000][k];
        }
        int ans = 0;
        int distance = Math.abs(endPos - startPos);
        int move = 1;
        if (startPos > endPos) {
            move = -1;
        }
        if (k - distance > 0 && (k - distance) % 2 == 0) {
            int res = backtrack(startPos - move, endPos, k - 1) % MOD;
            ans = (ans + res) % MOD;
        }
        int res = backtrack(startPos + move, endPos, k - 1) % MOD;
        ans = (ans + res) % MOD;
        memo[startPos + 1000][k] = ans;
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int startPos = 1;
        int endPos = 2;
        int k = 3;
        System.out.println("test: " + sol.numberOfWays(startPos, endPos, k));
    }
}