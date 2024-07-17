import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private List<int[]>[] graph;
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int m = edges.length;
        int n = m + 1;
        this.graph = (List<int[]>[])new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            graph[edge[1]].add(new int[]{edge[0], edge[2]});
        }
        int[] ans = new int[n];
        // count pairs that are connectable through the server i
        for (int i = 0; i < n; i++) {
            int res = 0;
            int count = 0;
            for (int[] adj : graph[i]) {
                int divisibleCount = dfs(adj[0], adj[1], i, signalSpeed);
                res += divisibleCount * count;
                count += divisibleCount;
            }
            ans[i] = res;
        }
        return ans;
    }

    private int dfs(int vertex, int totalWeight, int preVertex, int signalSpeed) {
        int res = 0;
        if (totalWeight % signalSpeed == 0) {
            res++;
        }
        for (int[] adj : graph[vertex]) {
            if (preVertex == adj[0]) continue;
            res += dfs(adj[0], totalWeight + adj[1], vertex, signalSpeed);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] edges = {{0,6,3},{6,5,3},{0,3,1},{3,2,7},{3,1,6},{3,4,2}};
        int signalSpeed = 3;
        // int[][] edges = {{0,1,1},{1,2,5},{2,3,13},{3,4,9},{4,5,2}};
        // int signalSpeed = 1;
        System.out.println("test: " + Arrays.toString(sol.countPairsOfConnectableServers(edges, signalSpeed)));
    }
}