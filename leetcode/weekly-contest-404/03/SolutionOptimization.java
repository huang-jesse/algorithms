import java.util.Arrays;

/**
 * (a+b) mod k = (b+c) mod k
 * 根据前置知识中的恒等式，移项得 (a+b−(b+c)) mod k = 0
 * 化简得 (a−c) mod k = 0 即 (a mod k) - (c mod k) = 0
 * 则有 a mod k = c mod k, 即 a 和 c 同模
 */
class SolutionOptimization {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        // dp[y][x] 表示最后两项模 k 分别为 y 和 x 的子序列的长度。
        // 因为 a 和 c 同模，即所有奇数项同模，所有偶数项同模，因此 dp[y][x] 由 dp[x][y] + 1 转移而来。
        int[][] dp = new int[k][k];
        int ans = 0;
        for (int x : nums) {
            x = x % k;
            for (int y = 0; y < k; y++) {
                dp[y][x] = dp[x][y] + 1;
                ans = Math.max(ans, dp[y][x]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] nums = {1,4,2,3,1,4};
        int k = 3; // 4
        System.out.println("test: " + sol.maximumLength(nums, k));
    }
}