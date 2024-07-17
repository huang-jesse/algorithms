class Solution {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int n = nums.length;
        int ans = 0;
        outter:for (int l = 0; l < n; l++) {
            if (nums[l] % 2 != 0 || nums[l] > threshold) {
                continue outter;
            }
            for (int r = l + 1; r < n; r++) {
                if (nums[r - 1] % 2 == nums[r] % 2 || nums[r] > threshold) {
                    int subLen = r - l;
                    ans = Math.max(ans, subLen);
                    continue outter;
                }
            }
            ans = Math.max(ans, n - l);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {3,2,5,4};
        // int threshold = 5;
        int[] nums = {2,3,4,5};
        int threshold = 4;
        System.out.println("test: " + sol.longestAlternatingSubarray(nums, threshold));
    }
}