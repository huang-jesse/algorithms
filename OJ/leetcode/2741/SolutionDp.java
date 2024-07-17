import java.util.Arrays;

class SolutionDp {
    private static final int MOD = (int)1e9 + 7;
    public int specialPerm(int[] nums) {
        int n = nums.length;
        // {mask, lastIndex}
        // mask: candidate indexes
        int maxMask = (1 << n) - 1;
        int[][] dp = new int[maxMask][n];
        // init state, all index has been chose.
        Arrays.fill(dp[0], 1);
        for (int mask = 1; mask < maxMask; mask++) {
            for (int i = 0; i < n; i++) {
                if ((mask >> i & 1) != 0) continue;
                // calculate dp[mask][i]
                for (int j = 0; j < n; j++) {
                    if ((mask >> j & 1) == 0 || (nums[j] % nums[i] != 0 && nums[i] % nums[j] != 0)) continue;
                    dp[mask][i] = (dp[mask][i] + dp[mask ^ (1 << j)][j]) % MOD;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = (ans + dp[maxMask ^ (1 << i)][i]) % MOD;
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionDp sol = new SolutionDp();
        int[] nums = {2,3,6};
        System.out.println("test: " + sol.specialPerm(nums));
    }
}