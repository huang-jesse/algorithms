class SolutionDP {
    public int deleteAndEarn(int[] nums) {
        int[] accNums = accumulateNums(nums);
        return dpFunc(accNums);
    }

    private int[] accumulateNums(int[] nums) {
        int numOfMax = 0;
        for (int i = 0; i < nums.length; i++) {
            numOfMax = Math.max(nums[i], numOfMax);
        }
        int[] accNums = new int[numOfMax+1];
        for (int i = 0; i < nums.length; i++) {
            accNums[nums[i]] += nums[i];
        }
        return accNums;
    }

    private int dpFunc(int[] accNums) {
        int len = accNums.length;
        if (len == 2)
            return accNums[1];
        int[] dp = new int[len];
        // boundary
        dp[1] = accNums[1];
        dp[2] = Math.max(accNums[1], accNums[2]);
        // dp
        for (int i = 3; i < len; i++) {
            dp[i] = Math.max(accNums[i] + dp[i-2], dp[i-1]);
        }
        return dp[len-1];
    }

    public static void main(String[] args) {
        SolutionDP sol = new SolutionDP();
        int[] nums = new int[]{2,2,3,3,3,4};
        System.out.println("test: " + sol.deleteAndEarn(nums));
    }
}