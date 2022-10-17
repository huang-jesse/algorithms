class Solution {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int or = 0;
        int ans = 0;
        // sliding window
        for (int left = 0, right = 0; right < n; right++) {
            while ((or & nums[right]) > 0) {
                // clear left
                or = or ^ nums[left];
                left++;
            }
            or = or | nums[right];
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3,1,5,11,13};
        // int[] nums = {1,3,8,48,10};
        System.out.println("test: " + sol.longestNiceSubarray(nums));
    }
}