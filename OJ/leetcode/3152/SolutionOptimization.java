import java.util.Arrays;

class SolutionOptimization {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] preSum = new int[n];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1];
            if (nums[i] % 2 == nums[i - 1] % 2) preSum[i]++;
        }
        int m = queries.length;
        boolean[] ans = new boolean[m];
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int from = query[0];
            int to = query[1];
            if (preSum[to] - preSum[from] == 0) {
                ans[i] = true;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] nums = {4,3,1,6};
        int[][] quereis = {{0,2}, {2,3}}; // res: [false,true]
        System.out.println("test: " + Arrays.toString(sol.isArraySpecial(nums, quereis)));
    }
}