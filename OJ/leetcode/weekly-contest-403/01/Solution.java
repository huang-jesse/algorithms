import java.util.Arrays;

class Solution {
    public double minimumAverage(int[] nums) {
        double ans = Double.POSITIVE_INFINITY;
        Arrays.sort(nums);
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            double avg = (double)(nums[l] + nums[r]) / 2;
            ans = Math.min(ans, avg);
            l++;
            r--;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,3,7,8,9};
        System.out.println("test: " + sol.minimumAverage(nums));
    }
}