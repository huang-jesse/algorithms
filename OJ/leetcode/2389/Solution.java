import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int m = queries.length;
        int[] ans = new int[m];
        Arrays.sort(nums);
        for (int i = 0; i < m; i++) {
            int preSum = 0;
            int j = 0;
            while (j < n && preSum + nums[j] <= queries[i]) {
                preSum += nums[j];
                j++;
            }
            ans[i] = j;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4,5,2,1};
        int[] queries = {3,10,21};
        System.out.println("test: " + Arrays.stream(sol.answerQueries(nums, queries)).boxed().collect(Collectors.toList()));
    }
}