import java.util.Arrays;

class Solution {
    int max;
    int steps;
    long[][] memo;
    static final int MOD = (int)1e9 + 7;
    public int numWays(int steps, int arrLen) {
        if (steps == 1 && arrLen == 1) return 1;
        this.max = Math.min(1 + steps / 2, arrLen);
        this.steps = steps;

        this.memo = new long[steps+1][this.max + 1];
        for (int i = 0; i < steps+1; i++) {
            Arrays.fill(memo[i], -1);
        }

        long ans = dfs(0, 1);
        return (int)(ans % MOD);
    }

    private long dfs(int step, int index) {
        if (step > this.steps || index < 1 || index > this.max) {
            return 0;
        }
        if (step == this.steps && index == 1) {
            return 1;
        }
        if (this.memo[step][index] != -1) {
            return this.memo[step][index];
        }

        long left = dfs(step + 1, index - 1);
        long mid = dfs(step + 1, index);
        long right = dfs(step + 1, index + 1);

        long curNum = (left + mid + right) % MOD;
        this.memo[step][index] = curNum;
        return curNum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int steps = 4;
        int arrLen = 2;
        System.out.println("test: " + sol.numWays(steps, arrLen));
    }
}