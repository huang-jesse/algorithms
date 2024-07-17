class SolutionDp {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        // init data
        dp[0] = 1;
        // calculate dp from 1 to target
        for (int i = 1; i <= target; i++) {
            // caculate dp[i]
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] <= i) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        int target = 4;
        SolutionDp sol = new SolutionDp();
        System.out.println("combinationSum4: "+ sol.combinationSum4(nums, target));
    }
}