class SolutionBinarySearch {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (check(nums, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int[] nums, int k) {
        int n = nums.length;
        int or = 0;
        for (int i = 0, j = 0; i <= (n - k); i++) {
            // sliding window
            while (j - i < k && (or & nums[j]) == 0) {
                or = or | nums[j];
                j++;
            }
            // clear nums[i]
            or = or ^ nums[i];
            if (j - i == k) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SolutionBinarySearch sol = new SolutionBinarySearch();
        // int[] nums = {3,1,5,11,13};
        int[] nums = {1,3,8,48,10};
        System.out.println("test: " + sol.longestNiceSubarray(nums));
    }
}