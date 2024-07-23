import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = (int)(1e9 + 7);
    private static final int INF = (int)(1e9 + 7);
    private int[] nums;
    private Map<Integer, Integer>[][][] memo;

    public int sumOfPowers(int[] nums, int k) {
        int n = nums.length;
        if (n < k) return 0;

        this.nums = nums;
        Arrays.sort(nums);
        // {index, lastIndex, k, {minDiff, sumOfPower}}
        this.memo = (Map<Integer, Integer>[][][])new Map[n][n + 1][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                for (int l = 0; l <= k; l++) {
                    memo[i][j][l] = new HashMap<>();
                }
            }
        }
        // 初始时，lastIndex = n , minDiff = INF 表示无效的边界情况
        return backtrack(0, n, k, INF);
    }

    private int backtrack(int index, int lastIndex, int k, int minDiff) {
        int n = nums.length;
        if (k == 0) {
            return minDiff;
        }
        if (n - index < k) {
            // invalid
            return 0;
        }
        if (this.memo[index][lastIndex][k].containsKey(minDiff)) {
            return this.memo[index][lastIndex][k].get(minDiff);
        }

        int res = 0;
        // chose
        int nextMinDiff = minDiff;
        if (lastIndex != n) {
            int currentDiff = this.nums[index] - this.nums[lastIndex];
            if (currentDiff < minDiff) {
                nextMinDiff = currentDiff;
            }
        }
        res = backtrack(index + 1, index, k - 1, nextMinDiff);
        // not chose
        res += backtrack(index + 1, lastIndex, k, minDiff);
        res = res % MOD;

        this.memo[index][lastIndex][k].put(minDiff, res);
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {1,2,3,4};
        // int k = 3; // 4
        int[] nums = {4,3,-1};
        int k = 2; // 10
        // int[] nums = {1,1,1};
        // int k = 3; // 0
        System.out.println("test: " + sol.sumOfPowers(nums, k));
    }
}