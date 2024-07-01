import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    private Map<Integer, List<int[]>> graph;
    private int n;
    private int minTime;
    private int maxTime;
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        this.n = values.length;
        this.maxTime = maxTime;
        this.graph = new HashMap<>();
        this.minTime = maxTime;
        for (int[] edge : edges) {
            List<int[]> adjs = graph.computeIfAbsent(edge[0], val -> new ArrayList<int[]>());
            adjs.add(new int[]{edge[1], edge[2]});
            adjs = graph.computeIfAbsent(edge[1], val -> new ArrayList<int[]>());
            adjs.add(new int[]{edge[0], edge[2]});
            this.minTime = Math.min(edge[2], this.minTime);
        }
        boolean[] visited = new boolean[n];
        visited[0] = true;
        return backtrack(values, visited, 0, 0, values[0]);
    }

    private int backtrack(int[] values, boolean[] visited, int node, int preTime, int preQuality) {
        if (preTime > maxTime) return 0;
        if (preTime + minTime > maxTime) {
            return node == 0 ? preQuality : 0;
        }
        int res = 0;
        if (node == 0) {
            // there is a way that is not continue
            res = preQuality;
        }
        List<int[]> adjs = graph.getOrDefault(node, Collections.emptyList());
        for (int[] adj : adjs) {
            int nextNode = adj[0];
            int edgeTime = adj[1];
            if (visited[nextNode]) {
                res = Math.max(res, backtrack(values, visited, nextNode, preTime + edgeTime, preQuality));
            } else {
                visited[nextNode] = true;
                res = Math.max(backtrack(values, visited, nextNode, preTime + edgeTime, preQuality + values[nextNode]), res);
                visited[nextNode] = false;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] values = {1,2,3,4};
        int[][] edges = {{0,1,10},{1,2,11},{2,3,12},{1,3,13}};
        int maxTime = 50; // ans = 7
        System.out.println("test: " + sol.maximalPathQuality(values, edges, maxTime));
    }
}