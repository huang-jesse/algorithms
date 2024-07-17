import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    private static final int INF = 0x3fffffff;
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        // {nodei, {nodej, weight}}
        int[][] edgeArr = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(edgeArr[i], INF);
        for (int[] road : roads) {
            edgeArr[road[0]][road[1]] = Math.min(edgeArr[road[0]][road[1]], road[2]);
            edgeArr[road[1]][road[0]] = Math.min(edgeArr[road[1]][road[0]], road[2]);
        }
        int ans = 0;
        // mask represent nodes that removed
        for (int mask = 0; mask < (1 << n); mask++) {
            // build graph
            Map<Integer, List<int[]>> graph = new HashMap<>();
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) continue; // remove node
                int[] edge = edgeArr[i];
                for (int j = 0; j < n; j++) {
                    if (edge[j] == INF || ((mask >> j) & 1) == 1) continue; // remove node
                    List<int[]> adjs = graph.computeIfAbsent(i, o -> new ArrayList<>());
                    adjs.add(new int[]{j, edge[j]});
                }
            }
            if (check(n, maxDistance, mask, graph)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean check(int n, int maxDistance, int mask, Map<Integer, List<int[]>> graph) {
        // dijkstra
        for (int s = 0; s < n; s++) {
            if (((mask >> s) & 1) == 1) continue;
            int[] distanceTo = dijkstra(n, graph, s);
            // check
            for (int t = s + 1; t < n; t++) {
                if (((mask >> t) & 1) == 1) continue;
                if (distanceTo[t] > maxDistance) return false;
            }
        }
        return true;
    }

    private int[] dijkstra(int n, Map<Integer, List<int[]>> graph, int s) {
        int[] distanceTo = new int[n];
        Arrays.fill(distanceTo, INF);
        // {node, distance}
        PriorityQueue<int[]> minPq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        distanceTo[s] = 0;
        minPq.offer(new int[]{s, distanceTo[s]});
        while (!minPq.isEmpty()) {
            int[] min = minPq.poll();
            int u = min[0];
            List<int[]> adjs = graph.getOrDefault(u, Collections.EMPTY_LIST);
            for (int[] adj : adjs) {
                int v = adj[0];
                int weight = adj[1];
                if (distanceTo[v] > distanceTo[u] + weight) {
                    distanceTo[v] = distanceTo[u] + weight;
                    minPq.offer(new int[]{v, distanceTo[v]});
                }
            }
        }
        return distanceTo;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        int maxDistance = 12;
        // int[][] roads = {{0,1,2},{1,2,10},{0,2,10}}; // 5
        // int[][] roads = {{0,1,20},{0,1,10},{1,2,2},{0,2,2}}; // 7
        int[][] roads = {{1,0,11},{1,0,16},{0,2,13}}; // 5
        System.out.println("test: " + sol.numberOfSets(n, maxDistance, roads));
    }
}