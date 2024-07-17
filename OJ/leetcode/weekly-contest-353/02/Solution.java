import java.util.Arrays;

class Solution {
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = -1;
            for (int j = i + 1; j < n; j++) {
                if (dp[j] < 0) {
                    continue;
                }
                if (Math.abs(nums[j] - nums[i]) <= target) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,3,6,4,1,2};
        int target = 0;
        System.out.println("test: " + sol.maximumJumps(nums, target));
    }
}