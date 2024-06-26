import java.util.Arrays;

class Solution {
    private static final int MOD = (int)1e9 + 7;
    public int specialPerm(int[] nums) {
        int n = nums.length;
        // {mask, lastIndex}
        // mask: candidate indexes
        int[][] memo = new int[1 << n][n];
        for (int i = 0; i < (1 << n); i++) {
            Arrays.fill(memo[i], -1);
        }
        int maxMask = (1 << n) - 1;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = (ans + backtrack(memo, nums, maxMask ^ (1 << i), i)) % MOD;
        }
        return ans;
    }

    private int backtrack(int[][] memo, int[] nums, int mask, int lastIndex) {
        int n = nums.length;
        if (mask == 0) {
            // find a valid permutation
            return 1;
        }
        if (memo[mask][lastIndex] != -1) {
            return memo[mask][lastIndex];
        }
        // chose next num
        int count = 0;
        for (int i = 0; i < n; i++) {
            if ((((mask >> i) & 1) == 0) || (nums[i] % nums[lastIndex] != 0 && nums[lastIndex] % nums[i] != 0)) continue;
            count = (count + backtrack(memo, nums, mask ^ (1 << i), i)) % MOD;
        }
        memo[mask][lastIndex] = count;
        return count;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2,3,6};
        System.out.println("test: " + sol.specialPerm(nums));
    }
}