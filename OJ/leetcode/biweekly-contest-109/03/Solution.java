class Solution {
    private final long INF = Long.MAX_VALUE / 2;
    public long maxScore(int[] nums, int x) {
        int n = nums.length;
        long[][] dp = new long[n][2];
        if (nums[0] % 2 == 0) {
            dp[0][0] = nums[0];
            dp[0][1] = -INF;
        } else {
            dp[0][0] = -INF;
            dp[0][1] = nums[0];
        }

        for (int i = 1; i < n; i++) {
            if (nums[i] % 2 == 0) {
                // 偶数
                dp[i][0] = Math.max(dp[i - 1][0] + nums[i], dp[i - 1][1] + nums[i] - x);
                dp[i][1] = dp[i - 1][1];
            } else {
                // 奇数
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = Math.max(dp[i - 1][1] + nums[i], dp[i - 1][0] + nums[i] - x);
            }
        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {8,50,65,85,8,73,55,50,29,95,5,68,52,79};
        int x = 74;
        // int[] nums = {2,3,6,1,9,2};
        // int x = 5;
        System.out.println("test: " + sol.maxScore(nums, x));
    }
}