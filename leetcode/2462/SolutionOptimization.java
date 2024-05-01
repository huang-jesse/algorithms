import java.util.Arrays;
import java.util.PriorityQueue;

class SolutionOptimization {
    public long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length;
        long total = 0;
        if (2 * candidates + k > n) {
            // alway picked the k from costs[0...n-1]
            Arrays.sort(costs);
            for (int i = 0; i < k; i++) {
                total += costs[i];
            }
            return total;
        }

        PriorityQueue<Integer> leftMinPq = new PriorityQueue<>();
        PriorityQueue<Integer> rightMinPq = new PriorityQueue<>();
        for (int i = 0; i < candidates; i++) {
            leftMinPq.offer(costs[i]);
            rightMinPq.offer(costs[n - 1 - i]);
        }
        int l = candidates;
        int r = n - candidates - 1;
        // k sessions
        for (int i = 0; i < k; i++) {
            // hiring session
            if (leftMinPq.peek() <= rightMinPq.peek()) {
                // left index always less than right index
                total += leftMinPq.poll();
                leftMinPq.offer(costs[l++]);
            } else {
                total += rightMinPq.poll();
                rightMinPq.offer(costs[r--]);
            }
        }
        return total;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] costs = {17,12,10,2,7,2,11,20,8};
        int k = 3;
        int candidates = 4;
        System.out.println("test: " + sol.totalCost(costs, k, candidates));
    }
}