import java.util.Arrays;

class Solution {
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        int prefixSum = 0;
        for (int i = 0; i < n; i++) {
            int left = prefixSum;
            prefixSum += nums[i];
            int right = sum - prefixSum;
            if (left == right) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,7,3,6,5,6};
        System.out.println("test: " + sol.pivotIndex(nums));
    }
}