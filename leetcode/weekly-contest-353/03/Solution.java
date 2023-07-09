class Solution {
    public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] dp = new int[n][2];
        dp[n - 1][0] = 1;
        dp[n - 1][1] = 1;
        int ans = 1;
        for (int i = n - 2; i >= 0; i--) {
            dp[i][0] = 1;
            if (nums1[i] <= nums1[i + 1]) {
                dp[i][0] = Math.max(dp[i][0], 1 + dp[i + 1][0]);
            }
            if (nums1[i] <= nums2[i + 1]) {
                dp[i][0] = Math.max(dp[i][0], 1 + dp[i + 1][1]);
            }

            dp[i][1] = 1;
            if (nums2[i] <= nums1[i + 1]) {
                dp[i][1] = Math.max(dp[i][1], 1 + dp[i + 1][0]);
            }
            if (nums2[i] <= nums2[i + 1]) {
                dp[i][1] = Math.max(dp[i][1], 1 + dp[i + 1][1]);
            }
            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums1 = {1,3,1,2,3,3};
        // int[] nums2 = {2,2,4,2,3,3};
        int[] nums1 = {2,3,1};
        int[] nums2 = {1,2,1};
        System.out.println("test: " + sol.maxNonDecreasingLength(nums1, nums2));
    }
}