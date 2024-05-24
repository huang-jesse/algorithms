import java.util.Arrays;

class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length;
        // monotonic stack
        int[] monotonicStack = new int[k];
        // size of stack
        int m = 0;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int remainLen = n - i;
            while (m > 0 && m + remainLen > k && num < monotonicStack[m - 1]) {
                // pop the last element
                m--;
            }
            // push num
            if (m < k) {
                monotonicStack[m++] = num;
            }
        }
        return monotonicStack;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {2,4,3,3,5,4,9,6};
        // int k = 4;
        int[] nums = {3,5,2,6};
        int k = 3;
        System.out.println("test: " + Arrays.toString(sol.mostCompetitive(nums, k)));
    }
}