import java.util.Arrays;

class Solution {
    private int n;
    private Boolean[][] memo;
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) return false;
        Arrays.sort(nums);
        this.n = nums.length;
        int half = sum / 2;
        memo = new Boolean[n][half + 1];
        return backtrack(nums, 0, half);
    }

    private boolean backtrack(int[] nums, int index, int remain) {
        if (index == n) {
            if (remain == 0) {
                return true;
            } else {
                return false;
            }
        }
        if (this.memo[index][remain] != null) {
            return this.memo[index][remain];
        }

        boolean res = false;
        if (remain < nums[index]) {
            if (remain == 0) {
                res = true;
            }
        } else {
            // 选 或 不选
            res = backtrack(nums, index + 1, remain - nums[index]) || backtrack(nums, index + 1, remain);
        }
        this.memo[index][remain] = res;
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {1,5,11,5}; // true
        int[] nums = {1,4,5,5,7}; // true
        System.out.println("test: " + sol.canPartition(nums));
    }
}