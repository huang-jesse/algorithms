import java.util.Arrays;

class Solution {
    private static final int INF =0x3f3f3f3f;
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = INF;

        for (int i = 0; i + k - 1 < n; i++) {
            int min = nums[i];
            int max = nums[i+k-1];
            ans = Math.min(ans, max-min);
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