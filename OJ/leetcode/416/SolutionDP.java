import java.util.Arrays;

class SolutionDP {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) return false;
        Arrays.sort(nums);
        int n = nums.length;
        int half = sum / 2;

        boolean[][] dp = new boolean[n + 1][half + 1];
        dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= half; j++) {
                dp[i + 1][j] = dp[i][j];
                if (j >= nums[i]) {
                    dp[i + 1][j] |= dp[i][j - nums[i]];
                }
            }
        }
        return dp[n][half];
    }

    public static void main(String[] args) {
        SolutionDP sol = new SolutionDP();
        // int[] nums = {1,5,11,5}; // true
        int[] nums = {1,4,5,5,7}; // true
        System.out.println("test: " + sol.canPartition(nums));
    }
}