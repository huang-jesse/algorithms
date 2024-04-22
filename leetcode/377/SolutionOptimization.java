import java.util.Arrays;

class Solution {
    private Integer[] memo;
    public int combinationSum4(int[] nums, int target) {
        this.memo = new Integer[target + 1];
        return backtrack(nums, target);
    }

    private int backtrack(int[] nums, int target) {
        if (target == 0) {
            // 找到一个合法的组合
            return 1;
        }
        if (this.memo[target] != null) {
            return this.memo[target];
        } else {
            int count = 0;
            for (int num : nums) {
                if (num <= target) {
                    count += backtrack(nums, target - num);
                }
            }
            this.memo[target] = count;
            return count;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        int target = 4;
        Solution sol = new Solution();
        System.out.println("combinationSum4: "+ sol.combinationSum4(nums, target));
    }
}