import java.util.Arrays;

class Solution {
    public int minimumDifference(int[] nums, int k) {
        if (k == 1) {
            return 0;
        }
        int n = nums.length;
        Arrays.sort(nums);
        int ans = (int)1e5;
        for (int i = 0; i < n-k+1; i++) {
            int j = i+k-1;
            int diff = nums[j] - nums[i];
            ans = Math.min(ans, diff);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {9,4,1,7};
        int k = 2;
        System.out.println("test: " + sol.minimumDifference(nums, k));
    }
}