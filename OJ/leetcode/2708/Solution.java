class Solution {
    public long maxStrength(int[] nums) {
        int n = nums.length;
        long mx = nums[0];
        long mn = nums[0];
        for (int i = 1; i < n; i++) {
            long x = nums[i];
            long temp = mn;
            mn = Math.min(Math.min(mn, x), Math.min(mn * x, mx * x));
            mx = Math.max(Math.max(mx, x), Math.max(temp * x, mx * x));
        }
        return mx;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3,-1,-5,2,5,-9};
        System.out.println("test: " + sol.maxStrength(nums));
    }
}