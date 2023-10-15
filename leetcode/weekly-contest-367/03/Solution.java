import java.util.Arrays;

class Solution {
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        int[] suffixMin = new int[n];
        int[] suffixMax =new int[n];
        suffixMin[n - 1] = nums[n - 1];
        suffixMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
            suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
        }
        for (int i = 0; i < n - indexDifference; i++) {
            int current = nums[i];
            int j = i + indexDifference;
            int rightMin = suffixMin[j];
            int rightMax = suffixMax[j];
            if (Math.abs(current - rightMin) >= valueDifference) {
                // find answer
                for (int k = j; k < n; k++) {
                    if (nums[k] == rightMin) {
                        return new int[]{i, k};
                    }
                }
            } else if (Math.abs(current - rightMax) >= valueDifference) {
                // find answer
                for (int k = j; k < n; k++) {
                    if (nums[k] == rightMax) {
                        return new int[]{i, k};
                    }
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {5,1,4,1};
        // int indexDifference = 2;
        // int valueDifference = 4;
        int[] nums = {2,1};
        int indexDifference = 0;
        int valueDifference = 0;
        int[] ans = sol.findIndices(nums, indexDifference, valueDifference);
        System.out.println("test: " + Arrays.toString(ans));
    }
}