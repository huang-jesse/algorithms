import java.util.Arrays;

class Solution {
    public int[] numberGame(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int[] arr = new int[n];
        for (int i = 0; i + 1 < n; i += 2) {
            arr[i] = nums[i + 1];
            arr[i + 1] = nums[i];
        }
        return arr;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {5,4,2,3};
        System.out.println("test: " + Arrays.toString(sol.numberGame(nums)));
    }
}