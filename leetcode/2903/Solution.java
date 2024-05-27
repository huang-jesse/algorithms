import java.util.Arrays;

class Solution {
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        int maxIdx = 0;
        int minIdx = 0;
        for (int j = indexDifference; j < n; j++) {
            int i = j - indexDifference;
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            } else if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
            int value = nums[j];
            if (nums[maxIdx] - value >= valueDifference) {
                return new int[]{maxIdx, j};
            }
            if (value - nums[minIdx] >= valueDifference) {
                return new int[]{minIdx, j};
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {5,1,4,1};
        int indexDifference = 2;
        int valueDifference = 4;
        System.out.println("test: " + Arrays.toString(sol.findIndices(nums, indexDifference, valueDifference)));
    }
}