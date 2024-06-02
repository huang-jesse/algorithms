class Solution {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        int[] dpBitwise = new int[n];
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            ans = Math.min(ans, Math.abs(x - k));
            dpBitwise[i] = x;
            for (int j = i - 1; j >= 0 && (dpBitwise[j] & x) != dpBitwise[j]; j--) {
                dpBitwise[j] = dpBitwise[j] & x;
                ans = Math.min(ans, Math.abs(dpBitwise[j] - k));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,4,5};
        int k = 3;
        System.out.println("test: " + sol.minimumDifference(nums, k));
    }
}