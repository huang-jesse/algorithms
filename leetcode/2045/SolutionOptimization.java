import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SolutionOptimization {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<Integer>[] graph = (List<Integer>[])new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[] shortestPath = new int[n + 1];
        int[] secondShortestPath = new int[n + 1];
        Arrays.fill(shortestPath, -1);
        Arrays.fill(secondShortestPath, -1);
        // {node, pathLen}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0});
        shortestPath[1] = 0;
        while (secondShortestPath[n] == -1) {
            int[] currentNode = queue.poll();
            int current = currentNode[0];
            int len = currentNode[1];
            for (int next : graph[current]) {
                if (shortestPath[next] == -1) {
                    shortestPath[next] = len + 1;
                    queue.offer(new int[]{next, shortestPath[next]});
                } else if (len + 1 > shortestPath[next] && secondShortestPath[next] == -1) {
                    secondShortestPath[next] = len + 1;
                    queue.offer(new int[]{next, secondShortestPath[next]});
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < secondShortestPath[n]; i++) {
            if (ans % (2 * change) >= change) {
                int waitTime = 2 * change - (ans % (2 * change));
                ans += waitTime;
            }
            ans += time;
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int n = 5;
        int[][] edges = {{1,2},{1,3},{1,4},{3,4},{4,5}};
        int time = 3;
        int change = 5;
        System.out.println("test: " + sol.secondMinimum(n, edges, time, change));
    }
}