import java.util.Arrays;

class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        // dp[i][j] 表示，考虑 nums[i] 为结尾，以 (x + nums[i]) % k == j 的合法子序列得最大值
        int[][] dp = new int[n][k];
        // 所有项目初始值为 1 ，因为 nums[j] 有 1 个序列长度
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 1);
        }
        int ans = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int mod = (nums[j] + nums[i]) % k;
                dp[i][mod] = dp[j][mod] + 1;
                ans = Math.max(ans, dp[i][mod]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,4,2,3,1,4};
        int k = 3; // 4
        System.out.println("test: " + sol.maximumLength(nums, k));
    }
}