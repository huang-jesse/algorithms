import java.util.Arrays;

class Solution {
    private static final int MOD = (int)1e9 + 7;
    public int numSubseq(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int l = 0;
        int r = n - 1;
        int ans = 0;
        while (l <= r) {
            // 右指针左移
            while (l < r && nums[l] + nums[r] > target) {
                r--;
            }
            if (l <= r && nums[l] + nums[r] <= target) {
                // 合法子序列
                ans = (ans + subseqCount(l, r)) % MOD;
            }
            // 左指针右移
            l++;
        }
        return ans;
    }

    /**
     * 合计以 l 为最小值的子序列数量
     * @param l
     * @param r
     * @return
     */
    private static int subseqCount(int l, int r) {
        return qpow(2, r - l);
    }

    private static int qpow(int a, int n) {
        int ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                // 当前二进制最后一位为1
                ans = (int)((long)ans * a % MOD);
            }
            // a累乘
            a = (int)((long)a * a % MOD);
            // n右移一位
            n = n >> 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2,3,3,4,6,7};
        int target = 12;
        System.out.println("test: " + sol.numSubseq(nums, target));
    }
}