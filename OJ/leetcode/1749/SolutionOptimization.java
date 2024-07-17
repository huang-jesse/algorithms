class SolutionOptimization {
    public int maxAbsoluteSum(int[] nums) {
        int min = 0;
        int max = 0;
        int preSum = 0;
        for (int num : nums) {
            preSum += num;
            if (preSum > max) {
                max = preSum;
            } else if (preSum < min) {
                min = preSum;
            }
        }
        return max - min;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] nums = {1,-3,2,3,-4};
        System.out.println("test: " + sol.maxAbsoluteSum(nums));
    }
}