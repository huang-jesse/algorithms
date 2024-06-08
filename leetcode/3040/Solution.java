import java.util.Arrays;

class Solution {
    private int[][] memo;
    public int maxOperations(int[] nums) {
        int n = nums.length;
        this.memo = new int[n][n];
        initArr(this.memo);
        int ans = 0;
        ans = backtrack(nums, nums[0] + nums[1], 2, n - 1);
        initArr(this.memo);
        ans = Math.max(ans, backtrack(nums, nums[n - 2] + nums[n - 1], 0, n - 3));
        initArr(this.memo);
        ans = Math.max(ans, backtrack(nums, nums[0] + nums[n - 1], 1, n - 2));
        return ans + 1;
    }

    private void initArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            Arrays.fill(arr[i], -1);
        }
    }

    private int backtrack(int[] nums, int sum, int i, int j) {
        if (i >= j || j - i < 1) return 0;
        if (this.memo[i][j] != -1) return this.memo[i][j];
        int res = 0;
        if (nums[i] + nums[i + 1] == sum) {
            res = backtrack(nums, sum, i + 2, j) + 1;
        }
        if (nums[j - 1] + nums[j] == sum) {
            res = Math.max(res, backtrack(nums, sum, i, j - 2) + 1);
        }
        if (nums[i] + nums[j] == sum) {
            res = Math.max(res, backtrack(nums, sum, i + 1, j - 1) + 1);
        }
        this.memo[i][j] = res;
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3,2,1,2,3,4};
        System.out.println("test: " + sol.maxOperations(nums));
    }
}