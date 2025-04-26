import java.util.Arrays;
import java.util.List;

class Solution {
    /**
     * (preSum[r] − preSum[l]) % modulo = k % modulo -> (preSum[r] - k) % modulo = preSum[l] % modulo
     * @param nums
     * @param modulo
     * @param k
     * @return
     */
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] += preSum[i] + (nums.get(i) % modulo == k ? 1 : 0);
        }
        // 枚举右端点
        long ans = 0;
        int[] counter = new int[Math.min(modulo, n + 1)];
        for (int r = 0; r <= n; r++) {
            if (preSum[r] >= k) {
                int preSumL = (preSum[r] - k) % modulo;
                ans += counter[preSumL];
            }
            // 维护左端点
            counter[preSum[r] % modulo]++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // List<Integer> nums = Arrays.asList(3,2,4);
        // int modulo = 2;
        // int k = 1;
        List<Integer> nums = Arrays.asList(3,1,9,6);
        int modulo = 3;
        int k = 0;
        System.out.println("test: " + sol.countInterestingSubarrays(nums, modulo, k));
    }
}