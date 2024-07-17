import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSizeSubarray(int[] nums, int target) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        int loops = target / sum;
        int ans = loops * n;
        target %= sum;
        if (target == 0) {
            return ans;
        }
        int res = Integer.MAX_VALUE;
        Map<Long, Integer> preSumMap = new HashMap<>();
        long[] prefixSum = new long[n * 2];
        prefixSum[0] = nums[0];
        preSumMap.put(prefixSum[0], 0);
        for (int i = 1; i < 2 * n; i++) {
            int index = i % n;
            prefixSum[i] = nums[index] + prefixSum[i - 1];
            long diff = prefixSum[i] - target;
            if (preSumMap.containsKey(diff)) {
                // 存在一个可能的答案
                res = Math.min(res, i - preSumMap.get(diff));
            }
            preSumMap.put(prefixSum[i], i);
        }
        ans += res;
        if (res == Integer.MAX_VALUE) {
            return -1;
        } else {
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,1,1,2,3};
        // int target = 4;
        int target = 12;
        System.out.println("test: " + sol.minSizeSubarray(nums, target));
    }
}