import java.util.Arrays;

class Solution {
    public int minPairSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n/2; i++) {
            int left = nums[i];
            int right = nums[n-1-i];
            ans = Math.max(ans, left+right);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3,5,4,2,4,6};
        System.out.println("test: " + sol.minPairSum(nums));
    }
}