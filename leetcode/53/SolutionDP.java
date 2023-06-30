class SolutionDP {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int largestSum = nums[0];
        int ans = largestSum;
        for (int i = 1; i < n; i++) {
            largestSum = Math.max(largestSum + nums[i], nums[i]);
            ans = Math.max(ans, largestSum);
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionDP sol = new SolutionDP();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println("test: " + sol.maxSubArray(nums));
    }
}