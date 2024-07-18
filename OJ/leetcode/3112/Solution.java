import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    private static final int CAN_NOT_REACH = -1;
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : edges) {
            List<int[]> adjs1 = graph.computeIfAbsent(edge[0], o -> new ArrayList<>());
            adjs1.add(new int[]{edge[1], edge[2]});

            List<int[]> adjs2 = graph.computeIfAbsent(edge[1], o -> new ArrayList<>());
            adjs2.add(new int[]{edge[0], edge[2]});
        }
        int[] reachTimes = new int[n];
        Arrays.fill(reachTimes, CAN_NOT_REACH);
        // dijkstra
        // {vertex, pathTime}
        PriorityQueue<int[]> minPq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        minPq.offer(new int[]{0, 0});
        reachTimes[0] = 0;
        while (!minPq.isEmpty()) {
            int[] vertexInfo = minPq.poll();
            int vertex = vertexInfo[0];
            int pathTime = vertexInfo[1];
            if (pathTime > reachTimes[vertex]) continue;
            List<int[]> adjs = graph.getOrDefault(vertex, Collections.emptyList());
            for (int[] adj : adjs) {
                int nextVertex = adj[0];
                int weight = adj[1];
                int newReachTime = pathTime + weight;
                if (newReachTime < disappear[nextVertex] && (reachTimes[nextVertex] == CAN_NOT_REACH || reachTimes[nextVertex] > newReachTime)) {
                    reachTimes[nextVertex] = newReachTime;
                    minPq.offer(new int[]{nextVertex, reachTimes[nextVertex]});
                }
            }
        }
        return reachTimes;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        int[][] edges = {{0,1,2},{1,2,1},{0,2,4}};
        int[] disappear = {1,1,5};
        System.out.println("test: " + Arrays.toString(sol.minimumTime(n, edges, disappear)));
    }
}