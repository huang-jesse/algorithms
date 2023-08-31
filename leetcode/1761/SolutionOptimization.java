import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.cn/problems/minimum-degree-of-a-connected-trio-in-a-graph/solutions/2417898/yi-ge-tu-zhong-lian-tong-san-yuan-zu-de-wuv8o/
 */
class SolutionOptimization {
    public int minTrioDegree(int n, int[][] edges) {
        int[] degrees = new int[n + 1];
        Set<Integer>[] graph = new HashSet[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new HashSet<>();
        }
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            degrees[x]++;
            degrees[y]++;

            graph[x].add(y);
            graph[y].add(x);
        }
        List<Integer>[] directGraph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            directGraph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            if (degrees[x] < degrees[y] || (degrees[x] == degrees[y] && x < y)) {
                directGraph[x].add(y);
            } else {
                directGraph[y].add(x);
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j : directGraph[i]) {
                for (int k : directGraph[j]) {
                    if (graph[k].contains(i)) {
                        // find a trio
                        ans = Math.min(ans, degrees[i] + degrees[j] + degrees[k] - 6);
                    }
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int n = 6;
        int[][] edges = {{1,2},{1,3},{3,2},{4,1},{5,2},{3,6}};
        System.out.println("test: " + sol.minTrioDegree(n, edges));
    }
}