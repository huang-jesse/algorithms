import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        // indexes heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> nums[o1] - nums[o2]);
        for (int i = 0; i < n; i++) {
            minHeap.offer(i);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        int[] res = minHeap.stream().sorted().mapToInt(idx -> nums[idx]).toArray();
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2,1,3,3};
        int k = 2;
        System.out.println("test: " + Arrays.toString(sol.maxSubsequence(nums, k)));
    }
}