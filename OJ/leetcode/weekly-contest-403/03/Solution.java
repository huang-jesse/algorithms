
class Solution {
    private static final long INF = (long)(1e14) + 1;
    public long maximumTotalCost(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[n][2];
        dp[0][0] = nums[0];
        dp[0][1] = -INF;
        for (int i = 1; i < n; i++) {
            long preMax = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][0] = Math.max(preMax + nums[i], dp[i - 1][1] + nums[i]);
            dp[i][1] = dp[i - 1][0] - nums[i];
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,-1,1,-1}; // 4
        // int[] nums = {-3,-2,-1,4,5,-6}; // 13
        // int[] nums = {1,-2,3,4}; // 10
        System.out.println("test: " + sol.maximumTotalCost(nums));
    }
}