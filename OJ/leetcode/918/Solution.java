class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int pre = 0;
        // 子数组最大值（当子数组不跨过数组边界的时候，即不考虑循环数组的情况）
        int subMax = Integer.MIN_VALUE;
        int preMin = 0;
        // 最小子数组和（最小子数组可以为空，则和为 0）
        // 通过逆向思考，当子数组跨过数组边界时，子数组的最大值=数组和-最小子数组的和（不跨过数组边界）
        int subMin = 0;
        int sumArr = 0;
        for (int num : nums) {
            pre = Math.max(num, pre + num);
            subMax = Math.max(subMax, pre);

            preMin = Math.min(num, preMin + num);
            subMin = Math.min(subMin, preMin);
            sumArr += num;
        }
        if (subMin == sumArr) {
            // 当最小子数组的和等于数组和时，此时的最小子数组可以是整个数组，因此返回不跨边界的最大子数组和
            return subMax;
        } else {
            return Math.max(subMax, sumArr - subMin);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,-2,3,-2};
        System.out.println("test: " + sol.maxSubarraySumCircular(nums));
    }
}