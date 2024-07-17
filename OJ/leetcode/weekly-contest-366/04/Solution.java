import java.util.Arrays;
import java.util.List;

class Solution {
    private static final int MOD = (int)(1e9 + 7);
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCounter = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCounter[i]++;
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < k; i++) {
            int currentNum = 0;
            for (int j = 0; j < 32; j++) {
                if (bitCounter[j] == 0) {
                    continue;
                } else {
                    bitCounter[j]--;
                    currentNum += (1 << j);
                }
            }
            ans = (ans + (long)currentNum * currentNum) % MOD;
        }
        return (int)(ans % MOD);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<Integer> nums = Arrays.asList(2,6,5,8);
        int k = 2;
        System.out.println("test: " + sol.maxSum(nums, k));
    }
}