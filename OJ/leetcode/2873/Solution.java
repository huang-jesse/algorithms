class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        int[] suffixMax = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            suffixMax[i] = Math.max(suffixMax[i + 1], nums[i + 1]);
        }
        long ans = 0;
        int preMax = nums[0];
        for (int j = 1; j < n - 1; j++) {
            long value = (long)(preMax - nums[j]) * suffixMax[j];
            ans = Math.max(ans, value);
            preMax = Math.max(preMax, nums[j]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {12,6,1,2,7};
        System.out.println("test: " + sol.maximumTripletValue(nums));
    }
}