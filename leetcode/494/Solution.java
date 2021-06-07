import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<String, Integer> memo = new HashMap<>();
    public int findTargetSumWays(int[] nums, int target) {
        int sumOfNums = Arrays.stream(nums).sum();
        if ((sumOfNums - target) % 2 != 0) {
            return 0;
        }
        int sumOfSub = (sumOfNums - target) / 2;
        return dfs(nums, sumOfSub, 0, 0);
    }

    private int dfs(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                return 1;
            } else {
                return 0;
            }
        }
        String key = index + "_" + sum;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int count = 0;
        count += dfs(nums, target, index+1, sum+nums[index]);
        count += dfs(nums, target, index+1, sum);
        memo.put(key, count);
        return count;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,1,1,1,1};
        int target = 2;
        System.out.println("test: " + sol.findTargetSumWays(nums, target));
    }
}