import java.util.Arrays;

class Solution {
    private int[][] memo;
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        arr[0] = 1;
        arr[n + 1] = 1;
        this.memo = new int[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(memo[i], -1);
        }
        return backtrack(arr, 0, n + 1);
    }

    private int backtrack(int[] nums, int i, int j) {
        if (j - i <= 1) return 0;
        if (this.memo[i][j] != -1) return this.memo[i][j];
        int res = 0;
        for (int mid = i + 1; mid < j; mid++) {
            int current = nums[i] * nums[mid] * nums[j];
            res = Math.max(res, current + backtrack(nums, i, mid) + backtrack(nums, mid, j));
        }
        this.memo[i][j] = res;
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3,1,5,8};
        System.out.println("test: " + sol.maxCoins(nums));
    }
}