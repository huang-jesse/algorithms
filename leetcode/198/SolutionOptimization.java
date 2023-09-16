class SolutionOptimization {
    public int rob(int[] nums) {
        int n = nums.length;
        int first = 0;
        int second = 0;
        for (int i = 0; i < n; i++) {
            int newVal = Math.max(second, first + nums[i]);
            first = second;
            second = newVal;
        }
        return second;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] nums = {2,7,9,3,1};
        System.out.println("test: " + sol.rob(nums));
    }
}