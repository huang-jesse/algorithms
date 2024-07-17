class Solution {
    public int minimumSum(int[] nums) {
        int n = nums.length;
        int[] leftArr = new int[n];
        int[] rightArr = new int[n];
        leftArr[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftArr[i] = Math.min(leftArr[i - 1], nums[i]);
        }
        rightArr[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightArr[i] = Math.min(rightArr[i + 1], nums[i]);
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > leftArr[i] && nums[i] > rightArr[i]) {
                ans = Math.min(ans, nums[i] + leftArr[i] + rightArr[i]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {5,4,8,7,10,2};
        System.out.println("test: " + sol.minimumSum(nums));
    }
}