import java.util.Arrays;

class SolutionOptimization {
    private static final int MOD = (int)1e9 + 7;
    public int numSubseq(int[] nums, int target) {
        int n = nums.length;
        // 预处理 2 次幂
        int[] pow = new int[n];
        pow[0] = 1;
        for (int i = 1; i < n; i++) {
            pow[i] = pow[i - 1] * 2 % MOD;
        }

        Arrays.sort(nums);
        int l = 0;
        int r = n - 1;
        int ans = 0;
        while (l <= r) {
            if (nums[l] + nums[r] > target) {
                // 右指针左移
                r--;
            } else {
                ans = (ans + pow[r - l]) % MOD;
                // 左指针右移
                l++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] nums = {2,3,3,4,6,7};
        int target = 12;
        System.out.println("test: " + sol.numSubseq(nums, target));
    }
}