class Solution {
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        long ans = 0;
        for (int i = 0; i < n-1; i++) {
            int max = nums[i];
            int min = nums[i];
            for (int j = i+1; j < n; j++) {
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);
                ans += max - min;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4,-2,-3,4,1};
        System.out.println("test: " + sol.subArrayRanges(nums));
    }
}