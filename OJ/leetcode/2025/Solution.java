import java.util.HashMap;
import java.util.Map;

class Solution {
    public int waysToPartition(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        long[] suffixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
            suffixSum[n - 1 - i] = suffixSum[n - i] + nums[n - 1 - i];
        }
        // {diff, count}
        Map<Long, Integer> rightDifferenceMap = new HashMap<>();
        for (int p = 1; p < n; p++) {
            long diff = prefixSum[p] - suffixSum[p];
            rightDifferenceMap.merge(diff, 1, Integer::sum);
        }
        int ans = rightDifferenceMap.getOrDefault(0L, 0);
        // {diff, count}
        Map<Long, Integer> leftDifferenceMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // slide window
            if (i > 0) {
                long diff = prefixSum[i] - suffixSum[i];
                // add i of diff
                leftDifferenceMap.merge(diff, 1, Integer::sum);
                // remove i of diff
                rightDifferenceMap.merge(diff, -1, Integer::sum);
            }

            long delta = k - nums[i];
            int leftRes = leftDifferenceMap.getOrDefault(delta, 0);
            int rightRes = rightDifferenceMap.getOrDefault(-delta, 0);
            ans = Math.max(ans, leftRes + rightRes);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {22,4,-25,-20,-15,15,-16,7,19,-10,0,-13,-14};
        int k = -33; // 4
        // int[] nums = {0,0,0};
        // int k = 1; // 2
        System.out.println("test: " + sol.waysToPartition(nums, k));
    }
}