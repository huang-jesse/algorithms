class SolutionDP {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n+1][m+1];
        // base condition
        // dp[i][0] == 0, dp[0][j] == 0
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    // nums1[i-1] != nums2[j-1]
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        SolutionDP sol = new SolutionDP();
        int[] nums1 = {2,5,1,2,5};
        int[] nums2 = {10,5,2,1,5,2};
        // int[] nums1 = {1,3,7,1,7,5};
        // int[] nums2 = {1,9,2,5,1};
        System.out.println("test: " + sol.maxUncrossedLines(nums1, nums2));
    }
}