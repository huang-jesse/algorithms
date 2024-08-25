import java.util.Arrays;

class Solution {
    int[] nums;
    int per, n;
    boolean[] dp;

    public boolean canPartitionKSubsets(int[] nums, int k) {
        this.nums = nums;
        int all = Arrays.stream(nums).sum();
        if (all % k != 0) {
            return false;
        }
        per = all / k;
        Arrays.sort(nums);
        n = nums.length;
        if (nums[n - 1] > per) {
            return false;
        }
        dp = new boolean[1 << n];
        Arrays.fill(dp, true);
        return dfs((1 << n) - 1, 0);
    }

    public boolean dfs(int s, int p) {
        if (s == 0) {
            return true;
        }
        if (!dp[s]) {
            return dp[s];
        }
        dp[s] = false;
        for (int i = 0; i < n; i++) {
            int cur = p + nums[i];
            if (cur > per) {
                break;
            }
            if (((s >> i) & 1) != 0) {
                if (dfs(s ^ (1 << i), cur == per ? 0 : cur)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4,3,2,3,5,2,1};
        int k = 4; // true
        // int[] nums = {9,10,1,7,2,7,1,1,1,3};
        // int k = 3; // true
        // int[] nums = {2,2,2,2,3,4,5};
        // int k = 4; // false
        System.out.println("test: " + sol.canPartitionKSubsets(nums, k));
    }
}