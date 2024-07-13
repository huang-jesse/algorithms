class SolutionOptimization {
    public boolean canSortArray(int[] nums) {
        int n = nums.length;
        int preMax = 0;
        for (int i = 0; i < n;) {
            int mx = 0;
            int ones = Integer.bitCount(nums[i]);
            while (i < n && Integer.bitCount(nums[i]) == ones) {
                if (nums[i] < preMax) {
                    return false;
                }
                mx = Math.max(mx, nums[i++]);
            }
            preMax = mx;
        }
        return true;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] nums = {8,4,2,30,15}; // true
        // int[] nums = {3,16,8,4,2}; // false
        System.out.println("test: " + sol.canSortArray(nums));
    }
}