import java.util.PriorityQueue;

class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<Integer> leftMinPq = new PriorityQueue<>();
        PriorityQueue<Integer> rightMinPq = new PriorityQueue<>();
        int n = costs.length;
        int l = -1;
        int r = n;
        long total = 0;
        // k sessions
        for (int i = 0; i < k; i++) {
            while ((l + 1) < r && leftMinPq.size() < candidates) {
                leftMinPq.offer(costs[++l]);
            }
            while ((r - 1) > l && rightMinPq.size() < candidates) {
                rightMinPq.offer(costs[--r]);
            }
            // hiring session
            if (leftMinPq.isEmpty()) {
                total += rightMinPq.poll();
            } else if (rightMinPq.isEmpty()) {
                total += leftMinPq.poll();
            } else if (leftMinPq.peek() <= rightMinPq.peek()) {
                // left index always less than right index
                total += leftMinPq.poll();
            } else {
                total += rightMinPq.poll();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] costs = {17,12,10,2,7,2,11,20,8};
        int k = 3;
        int candidates = 4;
        System.out.println("test: " + sol.totalCost(costs, k, candidates));
    }
}