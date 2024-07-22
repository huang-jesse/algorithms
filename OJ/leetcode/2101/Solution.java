import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        List<Integer>[] graph = (ArrayList<Integer>[])new ArrayList[n];
        Arrays.setAll(graph, o -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            int x1 = bombs[i][0];
            int y1 = bombs[i][1];
            long rQuadratic = (long)bombs[i][2] * bombs[i][2];
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                int x2 = bombs[j][0];
                int y2 = bombs[j][1];
                long xDiff = x1 - x2;
                long yDiff = y1 - y2;
                long dsQuadratic = xDiff * xDiff + yDiff * yDiff;
                if (rQuadratic >= dsQuadratic) {
                    graph[i].add(j);
                }
            }
        }
        int ans = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited, false);
            int res = dfs(graph, visited, i);
            ans = Math.max(ans, res);
        }
        return ans;
    }

    private int dfs(List<Integer>[] graph, boolean[] visited, int vertex) {
        visited[vertex] = true;
        int res = 1;
        List<Integer> adjs = graph[vertex];
        for (int adj : adjs) {
            if (!visited[adj]) {
                res += dfs(graph, visited, adj);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] bombs = {{1,2,3},{2,3,1},{3,4,2},{4,5,3},{5,6,4}};
        System.out.println("test: " + sol.maximumDetonation(bombs));
    }
}