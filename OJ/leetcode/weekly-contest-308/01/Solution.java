import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int m = queries.length;
        int[] ans = new int[m];
        Arrays.sort(nums);
        for (int i = 0; i < m; i++) {
            int curQuery = queries[i];
            int subLen = 0;
            int preSum = 0;
            for (int j = 0; j < n; j++) {
                preSum += nums[j];
                if (preSum <= curQuery) {
                    subLen++;
                } else {
                    break;
                }
            }
            ans[i] = subLen;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {469781,45635,628818,324948,343772,713803,452081};
        // int[] queries = {816646,929491};
        int[] nums = {4,5,2,1};
        int[] queries = {3,10,21};
        System.out.println("test: " + Arrays.stream(sol.answerQueries(nums, queries)).boxed().collect(Collectors.toList()));
    }
}