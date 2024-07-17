import java.util.Arrays;
import java.util.List;

class Solution {
    private int[][] memo;
    public int minimumOperations(List<Integer> nums) {
        int n = nums.size();
        memo = new int[n][4];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return backtrack(nums, 0, 1);
    }

    private int backtrack(List<Integer> nums, int index, int pre) {
        int n = nums.size();
        if (index == n) {
            return 0;
        }
        int cur = nums.get(index);
        if (memo[index][pre] != -1) {
            return memo[index][pre];
        }

        int res;
        if (cur >= pre) {
            // 不操作当前项
            res = backtrack(nums, index + 1, cur);
            // 操作当前项（减少）
            for (int i = cur - 1; i >= pre; i--) {
                res = Math.min(res, 1 + backtrack(nums, index + 1, i));
            }
        } else {
            // cur < pre
            res = 1 + backtrack(nums, index + 1, pre);
        }
        memo[index][pre] = res;
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<Integer> nums = Arrays.asList(1,3,2,1,3,3);
        System.out.println("test: " + sol.minimumOperations(nums));
    }
}