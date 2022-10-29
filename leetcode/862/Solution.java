import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSums = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }
        int ans = n + 1;
        Deque<Integer> monotonousQueue = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            long curSum = prefixSums[i];
            while (!monotonousQueue.isEmpty() && curSum - prefixSums[monotonousQueue.peekFirst()] >= k) {
                ans = Math.min(ans, i - monotonousQueue.pollFirst());
            }
            while (!monotonousQueue.isEmpty() && curSum <= prefixSums[monotonousQueue.peekLast()]) {
                monotonousQueue.pollLast();
            }
            monotonousQueue.offerLast(i);
        }
        return ans > n ? -1 : ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2,-1,2};
        int k = 3;
        System.out.println("test: " + sol.shortestSubarray(nums, k));
    }
}