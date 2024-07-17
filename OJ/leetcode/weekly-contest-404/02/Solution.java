
class Solution {
    public int maximumLength(int[] nums) {
        int n = nums.length;
        // 只有奇数或偶数
        int[] res = new int[2];
        for (int num : nums) {
            res[num % 2]++;
        }

        // 交替奇偶
        int[][] dp = new int[n + 1][2];
        dp[0][0] = 0;
        dp[0][1] = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                dp[i + 1][0] = Math.max(dp[i][1] + 1, dp[i][0]);
                dp[i + 1][1] = dp[i][1];
            } else {
                // nums[i] % 2 == 1
                dp[i + 1][0] = dp[i][0];
                dp[i + 1][1] = Math.max(dp[i][0] + 1, dp[i][1]);
            }
        }
        return Math.max(Math.max(dp[n][0], dp[n][1]), Math.max(res[0], res[1]));
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,1,1,2,1,2}; // 6
        System.out.println("test: " + sol.maximumLength(nums));
    }
}