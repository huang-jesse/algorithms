import java.util.Arrays;

class Solution {
    public int maxNumOfMarkedIndices(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int l = 0;
        for (int r = (n + 1) / 2; r < n; r++) {
            if (nums[l] * 2 <= nums[r]) {
                l++;
            }
        }
        return l * 2;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3,5,2,4}; // res: 2
        System.out.println("test: " + sol.maxNumOfMarkedIndices(nums));
    }
}