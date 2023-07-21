import java.util.PriorityQueue;

class Solution {
    public int findMaxValueOfEquation(int[][] points, int k) {
        int ans = Integer.MIN_VALUE;
        int n = points.length;
        // 队列中数组格式为 {-points[i][0]+points[i][1], points[i][0]}，根据 points[i] 的 (-x+y) 倒序排序（最大堆）
        PriorityQueue<int[]> pqi = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        for (int j = 0; j < n; j++) {
            int x = points[j][0];
            int y = points[j][1];
            // 出队不符合要求的点（即当前点 - k > 队列中的x）
            while (!pqi.isEmpty() && x - pqi.peek()[1] > k) {
                pqi.poll();
            }
            if (!pqi.isEmpty()) {
                // 当前点可以与 pointi 点取到最大值
                int[] pointi = pqi.peek();
                ans = Math.max(ans, pointi[0] + x + y);
            }
            // 当前点入队
            pqi.offer(new int[]{-x + y, x});
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] points = {{1,3},{2,0},{5,10},{6,-10}};
        int k = 1;
        System.out.println("test: " + sol.findMaxValueOfEquation(points, k));
    }
}