class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int min = 0;
        int max = 0;
        int ans = 0;
        int preSum = 0;
        for (int num : nums) {
            preSum += num;
            ans = Math.max(ans, Math.abs(preSum - min));
            ans = Math.max(ans, Math.abs(preSum - max));
            min = Math.min(min, preSum);
            max = Math.max(max, preSum);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,-3,2,3,-4};
        System.out.println("test: " + sol.maxAbsoluteSum(nums));
    }
}