import java.util.Arrays;

class Solution {
    private final static int MOD = (int)(1e9 + 7);
    public int sumOfPower(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        int minPreSum = 0;
        // minCurrent 表示以当前 num 为最大值时，所有可能组合的最小值的和
        // 令此时 num 为 nums[i] ，则有当前 sumOfPower = nums[i]^2 * nums[i] + num^2 * (2^{0}*nums[i - 1]) + num^2 * (2^{1}*nums[i - 2]) + ... + num^2 * (2^{i-1}*nums[0])
        // 即 sumOfPower = nums[i]^2 * (nums[i] + 2^{0}*nums[i - 1] + 2^{1}*nums[i - 2] + ... + 2^{i-1}*nums[0])
        int minCurrent = 0;
        for (int num : nums) {
            minCurrent = (num + minPreSum) % MOD;
            long maxStrength = (long)num*num % MOD;
            int currentPower = (int)(maxStrength * minCurrent % MOD);
            ans = (ans + currentPower) % MOD;
            minPreSum = (minPreSum + minCurrent) % MOD;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2,1,4};
        System.out.println("test: " + sol.sumOfPower(nums));
    }
}