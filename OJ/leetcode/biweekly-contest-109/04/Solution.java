import java.util.ArrayList;
import java.util.List;

class Solution {
    static final int MOD = (int)(1e9 + 7);
    public int numberOfWays(int n, int x) {
        List<Integer> nums = new ArrayList<>();
        int k = 1;
        int powk = 1;
        while (k <= n) {
            nums.add(powk);
            k++;
            powk = (int)Math.pow(k, x);
        }
        int m = nums.size();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            int currentNum = nums.get(i);
            for (int j = n; j >= currentNum; j--) {
                dp[j] = (dp[j] + dp[j - currentNum]) % MOD;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 10;
        int x = 2;
        System.out.println("test: " + sol.numberOfWays(n, x));
    }
}