import java.util.Arrays;

class SolutionOptimization {
    private int[][] memo;
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        this.memo = new int[n][2001];
        for (int i = 0; i < n; i++) Arrays.fill(this.memo[i], -1);
        return backtrack(nums, 0, target);
    }

    private int backtrack(int[] nums, int index, int target) {
        int n = nums.length;
        if (index == n) {
            return target == 0 ? 1 : 0;
        }
        // invalid target state
        if (target + 1000 < 0 || target + 1000 > 2000) return 0;
        if (this.memo[index][target + 1000] != -1) {
            return this.memo[index][target + 1000];
        }
        int res = 0;
        res += backtrack(nums, index + 1, target + nums[index]);
        res += backtrack(nums, index + 1, target - nums[index]);
        this.memo[index][target + 1000] = res;
        return res;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] nums = {1,1,1,1,1};
        int target = 3; // 5
        // int[] nums = {2,107,109,113,127,131,137,3,2,3,5,7,11,13,17,19,23,29,47,53};
        // int target = 1000; // 0
        System.out.println("test: " + sol.findTargetSumWays(nums, target));
    }
}