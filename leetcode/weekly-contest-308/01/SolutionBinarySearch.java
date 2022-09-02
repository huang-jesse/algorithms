import java.util.Arrays;
import java.util.stream.Collectors;

class SolutionBinarySearch {
    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int m = queries.length;
        Arrays.sort(nums);
        int[] preSum = new int[n];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int curQuery = queries[i];
            int index = binarySearchRightBoundary(preSum, curQuery);
            int subLen = index + 1;
            ans[i] = subLen;
        }
        return ans;
    }

    private int binarySearchRightBoundary(int[] arr, int target) {
        if (target < arr[0]) {
            return -1;
        }
        int n = arr.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = left + ((right - left + 1) >> 1);
            if (arr[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        SolutionBinarySearch sol = new SolutionBinarySearch();
        // int[] nums = {469781,45635,628818,324948,343772,713803,452081};
        // int[] queries = {816646,929491};
        // int[] nums = {4,5,2,1};
        // int[] queries = {3,10,21};
        int[] nums = {2,3,4,5};
        int[] queries = {1};
        System.out.println("test: " + Arrays.stream(sol.answerQueries(nums, queries)).boxed().collect(Collectors.toList()));
    }
}