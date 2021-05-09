import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSumMinProduct(int[] nums) {
        int len = nums.length;
        long[] preSum = new long[len];
        preSum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i-1] + nums[i];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> rightMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peekFirst()]) {
                rightMap.put(stack.removeFirst(), i);
            }
            stack.addFirst(i);
        }

        stack.clear();
        Map<Integer, Integer> leftMap = new HashMap<>();
        for (int i = len-1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peekFirst()]) {
                leftMap.put(stack.removeFirst(), i);
            }
            stack.addFirst(i);
        }
        // calculate maxSumMin
        long ans = 0;
        for (int i = 0; i < len; i++) {
            int targetMin = nums[i];
            int left = leftMap.getOrDefault(i, -1) + 1;
            int right = rightMap.getOrDefault(i, len) - 1;
            ans = Math.max(ans, getMaxSumMinOfTargetMin(preSum, targetMin, left, right));
        }

        int mod = (int)1e9 + 7;
        return (int)(ans % mod);
    }

    private long getMaxSumMinOfTargetMin(long[] preSum, int targetMin, int left, int right) {
        long outOfRange = left > 0 ? preSum[left-1] : 0;
        long boundaryOfRight = preSum[right];
        long sum = boundaryOfRight - outOfRange;
        return sum * targetMin;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = new int[]{1,2,3,2};
        // int[] nums = new int[]{2,3,3,1,2};
        // int[] nums = new int[]{3,1,5,6,4,2};
        // int[] nums = new int[]{2,5,4,2,4,5,3,1,2,4};
        System.out.println("test: " + sol.maxSumMinProduct(nums));
    }
}