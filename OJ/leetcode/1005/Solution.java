import java.util.PriorityQueue;

class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            minQueue.offer(nums[i]);
        }
        for (int i = 0; i < k; i++) {
            int cur = minQueue.poll();
            minQueue.offer(-cur);
        }
        return minQueue.stream().mapToInt(o -> (int)o).sum();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4,2,3};
        int k = 1;
        System.out.println("test: " + sol.largestSumAfterKNegations(nums, k));
    }
}