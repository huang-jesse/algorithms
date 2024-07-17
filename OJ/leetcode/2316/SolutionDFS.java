import java.util.ArrayList;
import java.util.List;

class SolutionDFS {
    private boolean[] visited;
    public long countPairs(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        this.visited = new boolean[n];
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            int count = dfs(graph, i);
            ans += (long)(n - count) * count;
        }
        return ans / 2;
    }

    private int dfs(List<Integer>[] graph, int i) {
        visited[i] = true;
        int count = 1;
        for (int next : graph[i]) {
            if (!visited[next]) {
                count += dfs(graph, next);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        SolutionDFS sol = new SolutionDFS();
        int n = 7;
        int[][] edges = {{0,2},{0,5},{2,4},{1,6},{5,4}};
        // int n = 11;
        // int[][] edges = {{5,0},{1,0},{10,7},{9,8},{7,2},{1,3},{0,2},{8,5},{4,6},{4,2}};
        System.out.println("test: " + sol.countPairs(n, edges));
    }
}