import java.util.Arrays;

class Solution {
    private static int MOD = (int)(1e9 + 7);
    public int sumDistance(int[] nums, String s, int d) {
        int n = nums.length;
        long[] robots = new long[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'L') {
                robots[i] = (long)nums[i] - d;
            } else {
                // R
                robots[i] = (long)nums[i] + d;
            }
        }
        Arrays.sort(robots);
        long preSum = robots[0];
        long ans = 0;
        for (int i = 1; i < n; i++) {
            ans = (ans + i * robots[i] - preSum) % MOD;

            preSum += robots[i];
        }
        return (int)ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {-2,0,2};
        String s = "RLL";
        int d = 3;
        System.out.println("test: " + sol.sumDistance(nums, s, d));
    }
}