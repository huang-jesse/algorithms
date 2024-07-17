class Solution {
    private static final int MAX = (int)(1e9 + 7);
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        int[] suffixSum = new int[n];
        int prefix = 0;
        int suffix = 0;
        for (int i = 0; i < n; i++) {
            prefix += nums[i];
            suffix += nums[n - 1 - i];
            prefixSum[i] = prefix;
            suffixSum[n - 1 - i] = suffix;
        }
        // Find max of right bound and max of left bound in every index, and calculate the sum.
        int[] rightBoundSumArr = new int[n];
        int maxOfPrefixSum = -MAX;
        int[] leftBoundSumArr = new int[n];
        int maxOfSuffixSum = -MAX;
        for (int i = n - 1; i >= 0; i--) {
            // right -> left
            rightBoundSumArr[i] = maxOfPrefixSum - prefixSum[i];
            maxOfPrefixSum = Math.max(maxOfPrefixSum, prefixSum[i]);
            // left -> right
            leftBoundSumArr[n - 1 - i] = maxOfSuffixSum - suffixSum[n - 1 - i];
            maxOfSuffixSum = Math.max(maxOfSuffixSum, suffixSum[n - 1 - i]);
        }

        // Calculate max subArray in every index.
        int largestSum = -MAX;
        for (int i = 0; i < n; i++) {
            int subSum = Math.max(leftBoundSumArr[i], 0) + nums[i] + Math.max(rightBoundSumArr[i], 0);
            largestSum = Math.max(largestSum, subSum);
        }
        return largestSum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println("test: " + sol.maxSubArray(nums));
    }
}