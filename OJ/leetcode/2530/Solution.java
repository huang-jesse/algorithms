import java.util.PriorityQueue;

class Solution {
    public long maxKelements(int[] nums, int k) {
        long ans = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        for (Integer num : minHeap) {
            maxHeap.offer(num);
        }
        for (int i = 0; i < k; i++) {
            int current = maxHeap.poll();
            ans += current;
            maxHeap.offer((current + 2) / 3);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {10,10,10,10,10};
        int k = 5;
        System.out.println("test: " + sol.maxKelements(nums, k));
    }
}