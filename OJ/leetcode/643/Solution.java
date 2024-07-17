class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int maxSubSum = Integer.MIN_VALUE;
        int currentSubSum = 0;
        int l = 0;
        int r = 0;
        while (l + k - 1 < n) {
            while (r - l + 1 <= k) {
                currentSubSum += nums[r];
                r++;
            }
            maxSubSum = Math.max(maxSubSum, currentSubSum);
            currentSubSum -= nums[l];
            l++;
        }
        return (double)maxSubSum / k;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,12,-5,-6,50,3};
        int k = 4;
        System.out.println("test: " + sol.findMaxAverage(nums, k));
    }
}