class Solution {
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        int[] preCounter = new int[n + 1];
        long ans = 0;
        for (int j = 0; j < n; j++) {
            int suffix = 0;
            for (int k = n - 1; k > j; k--) {
                if (nums[j] > nums[k]) {
                    ans += (long)preCounter[nums[k]] * suffix;
                } else {
                    suffix++;
                }
            }

            for (int x = nums[j] + 1; x <= n; x++) {
                preCounter[x]++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,3,2,4,5};
        System.out.println("test: " + sol.countQuadruplets(nums));
    }
}