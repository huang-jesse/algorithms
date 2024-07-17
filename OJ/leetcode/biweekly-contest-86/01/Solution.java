class Solution {
    public boolean findSubarrays(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int pre = nums[i] + nums[i + 1];
            for (int j = i + 1; j < n - 1; j++) {
                int cur = nums[j] + nums[j + 1];
                if (pre == cur) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {4,2,4};
        int[] nums = {1,2,3,4,1};
        System.out.println("test: " + sol.findSubarrays(nums));
    }
}