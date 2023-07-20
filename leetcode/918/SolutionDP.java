class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        // leftMax[i] 表示以 0 为首的子数组，到 i 为止，最大的子数组的和
        int[] leftMax = new int[n];
        // 保证子数组不为空
        leftMax[0] = nums[0];
        int preSum = nums[0];
        int pre = nums[0];
        // 分情况讨论：1.当子数组不跨数组边界时（即不考虑循环数组的情况），子数组的最大和
        int res = nums[0];
        for (int i = 1; i < n; i++) {
            preSum += nums[i];
            leftMax[i] = Math.max(leftMax[i - 1], preSum);

            pre = Math.max(pre + nums[i], nums[i]);
            res = Math.max(res, pre);
        }

        int suffixSum = 0;
        // 分情况讨论：2.当子数组跨数组边界时，子数组的最大和
        // 固定右侧子数组 [j...n-1] ，从右往左遍历；右侧子数组和 + leftMax[j-1] = 当前子数组最大值
        for (int j = n - 1; j > 0; j--) {
            suffixSum += nums[j];
            res = Math.max(res, suffixSum + leftMax[j - 1]);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,-2,3,-2};
        System.out.println("test: " + sol.maxSubarraySumCircular(nums));
    }
}