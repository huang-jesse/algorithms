class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long ans = 0;
        int lastExlcusiveIndex = -1;
        int lastMinIndex = -1;
        int lastMaxIndex = -1;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == minK) {
                lastMinIndex = i;
            }
            if (num == maxK) {
                lastMaxIndex = i;
            }
            if (num < minK || num > maxK) {
                lastExlcusiveIndex = i;
            }
            int count = Math.min(lastMinIndex, lastMaxIndex) - lastExlcusiveIndex;
            if (count > 0) {
                ans += count;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,3,5,2,7,5};
        int minK = 1;
        int maxK = 5; // ans = 2
        System.out.println("test: " + sol.countSubarrays(nums, minK, maxK));
    }
}