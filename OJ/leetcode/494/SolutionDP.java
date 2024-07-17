import java.util.Arrays;

class SolutionDP {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int s = Arrays.stream(nums).sum();
        if (Math.abs(target) > s) return 0;

        int[][] dp = new int[n + 1][2 * s + 1];
        // init state
        dp[0][0 + s] = 1;
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = -s; j <= s; j++) {
                if (j + s - x >= 0) {
                    // '+' operate
                    dp[i][j + s] += dp[i - 1][j + s - x];
                }
                if (j + s + x <= 2 * s) {
                    // '-' operate
                    dp[i][j + s] += dp[i - 1][j + s + x];
                }
            }
        }
        return dp[n][target + s];
    }

    public static void main(String[] args) {
        SolutionDP sol = new SolutionDP();
        int[] nums = {1,1,1,1,1};
        int target = 3; // 5
        // int[] nums = {2,107,109,113,127,131,137,3,2,3,5,7,11,13,17,19,23,29,47,53};
        // int target = 1000; // 0
        System.out.println("test: " + sol.findTargetSumWays(nums, target));
    }
}