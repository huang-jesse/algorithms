class Solution {
    private Boolean[] memo;
    public boolean validPartition(int[] nums) {
        memo = new Boolean[nums.length];
        return dfs(nums, 0, nums.length-1);
    }

    private boolean dfs(int[] nums, int start, int n) {
        if (start > n) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        boolean ans = false;
        // 2
        if (n - start >= 1 && nums[start] == nums[start+1]) {
            ans = ans || dfs(nums, start + 2, n);
        }

        // 3
        if (n - start >= 2) {
            if (nums[start] == nums[start+1] && nums[start+1] == nums[start+2]) {
                ans = ans || dfs(nums, start + 3, n);
            } else if (nums[start] == nums[start+1] - 1 && nums[start+1] == nums[start+2] - 1) {
                ans = ans || dfs(nums, start + 3, n);
            }
        }
        memo[start] = ans;
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4,4,4,5,6};
        System.out.println("test: " + sol.validPartition(nums));
    }
}