class Solution {
    public int maxSubArray(int[] nums) {
        int preSum = 0;
        int minPreSum = 0;
        int ans = Integer.MIN_VALUE;
        for (int num : nums) {
            preSum += num;
            ans = Math.max(ans, preSum - minPreSum);
            minPreSum = Math.min(minPreSum, preSum);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,};
        System.out.println("test: " + sol.maxSubArray(nums));
    }
}