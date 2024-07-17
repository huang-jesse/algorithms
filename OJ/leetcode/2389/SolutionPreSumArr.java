import java.util.Arrays;
import java.util.stream.Collectors;

class SolutionPreSumArr {
    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int m = queries.length;
        Arrays.sort(nums);
        int[] preSumArr = new int[n];
        preSumArr[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSumArr[i] = preSumArr[i - 1] + nums[i];
        }

        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            ans[i] = binarySearchRightBoundary(preSumArr, queries[i]) + 1;
        }
        return ans;
    }

    private int binarySearchRightBoundary(int[] arr, int target) {
        if (arr[0] > target) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (arr[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        SolutionPreSumArr sol = new SolutionPreSumArr();
        int[] nums = {4,5,2,1};
        int[] queries = {3,10,21};
        System.out.println("test: " + Arrays.stream(sol.answerQueries(nums, queries)).boxed().collect(Collectors.toList()));
    }
}