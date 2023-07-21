import java.util.Deque;
import java.util.LinkedList;

class SolutionMonotonicQueue {
    public int findMaxValueOfEquation(int[][] points, int k) {
        int ans = Integer.MIN_VALUE;
        int n = points.length;
        // 队列中数组格式为 {-points[i][0]+points[i][1], points[i][0]}，单调队列根据 points[i] 的 (-x+y) 排序，最大值在队首
        Deque<int[]> monotonicQueue = new LinkedList<>();
        for (int j = 0; j < n; j++) {
            int x = points[j][0];
            int y = points[j][1];
            // 出队不符合要求的点（即当前点 - k > 队列中的x）
            while (!monotonicQueue.isEmpty() && x - monotonicQueue.peekFirst()[1] > k) {
                monotonicQueue.pollFirst();
            }
            if (!monotonicQueue.isEmpty()) {
                // 当前点可以与 pointi 点取到最大值
                int[] pointi = monotonicQueue.peekFirst();
                ans = Math.max(ans, pointi[0] + x + y);
            }
            // 如果当前点 (-x+y) 大于队列末尾，则将队列末尾出列以维持单调性。
            int currentXY = -x + y;
            while (!monotonicQueue.isEmpty() && currentXY >= monotonicQueue.peekLast()[0]) {
                monotonicQueue.pollLast();
            }
            // 当前点入队
            monotonicQueue.offerLast(new int[]{-x + y, x});
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionMonotonicQueue sol = new SolutionMonotonicQueue();
        int[][] points = {{1,3},{2,0},{5,10},{6,-10}};
        int k = 1;
        System.out.println("test: " + sol.findMaxValueOfEquation(points, k));
    }
}