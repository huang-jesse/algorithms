class SolutionDP {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // 由于环形数组，因此要选第0项只能不选第n-1项，反之亦然
        return Math.max(dp(nums, 0, n - 2), dp(nums, 1, n - 1));
    }

    private int dp(int[] nums, int left, int right) {
        int first = nums[left];
        int second = Math.max(nums[left], nums[left + 1]);
        for (int i = left + 2; i <= right; i++) {
            int temp = Math.max(second, first + nums[i]);
            first = second;
            second = temp;
        }
        return second;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,1,2,3,3};
        SolutionDP sol = new SolutionDP();
        System.out.println("rob: "+ sol.rob(nums));
    }
}