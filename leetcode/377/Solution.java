import java.util.Arrays;

class Solution {
    Integer[] memo;
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        memo = new Integer[target+1];
        return findCombinationSum(0, nums, target);
    }

    private int findCombinationSum(int pre, int[] nums, int target) {
        if (memo[pre] != null) return memo[pre];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = pre + nums[i];
            if (cur > target) {
                break;
            } else if (cur < target) {
                sum += findCombinationSum(cur, nums, target);
            } else {
                // cur == target
                sum += 1;
                break;
            }
        }
        memo[pre] = sum;
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        int target = 4;
        Solution sol = new Solution();
        System.out.println("combinationSum4: "+ sol.combinationSum4(nums, target));
    }
}