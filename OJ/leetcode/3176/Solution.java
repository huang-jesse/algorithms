import java.util.Arrays;

class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);

        int ans = 1;
        dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = 1;
            for (int l = 0; l <= k; l++) {
                for (int j = i - 1; j >= 0; j--) {
                    int add = nums[i] != nums[j] ? 1 : 0;
                    if (l - add >= 0 && dp[j][l - add] != -1) {
                        dp[i][l] = Math.max(dp[i][l], dp[j][l - add] + 1);
                    }
                }
                ans = Math.max(ans, dp[i][l]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,1,1,3};
        int k = 2;
        System.out.println("test: " + sol.maximumLength(nums, k));
    }
}