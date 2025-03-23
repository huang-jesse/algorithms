class SolutionOptimization {
    public long maximumOr(int[] nums, int k) {
        int n = nums.length;
        int[] suffix = new int[n];
        // suffix[i] 表示 nums[i + 1] 到 nums[n - 1] 的 or，其中 suffix[n - 1] = 0
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] | nums[i + 1];
        }
        long ans = 0L;
        int pre = 0;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            ans = Math.max(ans, pre | ((long)num << k) | suffix[i]);
            pre |= num;
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] nums = {8,1,2};
        int k = 2;
        System.out.println("test: " + sol.maximumOr(nums, k));
    }
}