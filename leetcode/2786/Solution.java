class Solution {
    public long maxScore(int[] nums, int x) {
        int n = nums.length;
        long[] dp = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        dp[nums[0] % 2] = nums[0];
        for (int i = 1; i < n; i++) {
            long num = nums[i];
            int parity = nums[i] % 2;
            dp[parity] = Math.max(dp[parity] + num, dp[1 - parity] + num - x);
        }
        return Math.max(dp[0], dp[1]);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {2,3,6,1,9,2};
        // int x = 5; // 13
        int[] nums = {8,50,65,85,8,73,55,50,29,95,5,68,52,79};
        int x = 74; // 470
        System.out.println("test: " + sol.maxScore(nums, x));
    }
}