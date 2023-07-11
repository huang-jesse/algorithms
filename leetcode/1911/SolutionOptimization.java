class SolutionOptimization {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long ans = 0L;
        int preNum = 0;
        for (int i = 0; i < n; i++) {
            int curNum = nums[i];
            if (preNum < curNum) {
                ans += (curNum - preNum);
            }
            preNum = curNum;
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] nums = {4,2,3,7,2,1,4};// ans = 12
        System.out.println("test: " + sol.maxAlternatingSum(nums));
    }
}