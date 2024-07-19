import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/solutions/2525946/dai-ni-fa-ming-floyd-suan-fa-cong-ji-yi-m8s51/
 */
class SolutionDijkstra {
    private static final int INF = 0x3fffffff;
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        List<int[]>[] graph = (ArrayList<int[]>[])new ArrayList[n];
        Arrays.setAll(graph, o -> new ArrayList<>());
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            graph[edge[1]].add(new int[]{edge[0], edge[2]});
        }
        int ans = -1;
        int minReachableCount = INF;
        Dijkstra dijkstra = new Dijkstra(n, graph);
        for (int s = 0; s < n; s++) {
            dijkstra.dijkstra(s);
            int[] d = dijkstra.d;
            int reachableCount = 0;
            for (int i = 0; i < n; i++) {
                if (d[i] <= distanceThreshold) reachableCount++;
            }
            if (reachableCount <= minReachableCount) {
                ans = s;
                minReachableCount = reachableCount;
            }
            // boolean check = dijkstra.shortestPathInfoCorrectCheck(s);
            // if (!check) {
            //     System.out.println("shortestPathInfoCorrectCheck failed!");
            //     return -1;
            // }
        }
        return ans;
    }

    static class Dijkstra {
        private static final int INF = 0x3fffffff;
        private int n;
        private List<int[]>[] graph;
        private int[] d;
        private int[] p;

        // for shortestPathInfoCorrectCheck
        private int[][] weights;

        public Dijkstra(int n, List<int[]>[] graph) {
            this.n = n;
            this.graph = graph;
            this.d = new int[n];
            this.p = new int[n];

            // for shortestPathInfoCorrectCheck
            this.weights = new int[n][n];
            for (int i = 0; i < n; i++) Arrays.fill(weights[i], INF);
            for (int i = 0; i < n; i++) {
                for (int[] adj : graph[i]) {
                    weights[i][adj[0]] = adj[1];
                }
            }
        }

        public void dijkstra(int s) {
            Arrays.fill(d, INF);
            Arrays.fill(p, -1);
            // {vertex, pathLen}
            PriorityQueue<int[]> minPq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
            d[s] = 0;
            minPq.offer(new int[]{s, d[s]});
            // iterations all vertex
            while (!minPq.isEmpty()) {
                // chosen vertex u
                int[] uInfo = minPq.poll();
                int u = uInfo[0];
                int du = uInfo[1];
                if (du > d[u]) continue; // this vertex was already relaxations

                // relaxations vertex u
                List<int[]> adjs = graph[u];
                for (int[] adj : adjs) {
                    int v = adj[0];
                    int weight = adj[1]; // len of (u -> v)
                    if (d[v] > d[u] + weight) {
                        // successful relaxation
                        d[v] = d[u] + weight;
                        p[v] = u;
                        minPq.offer(new int[]{v, d[v]});
                    }
                }
            }
        }

        public void dijkstraBruteforce(int s) {
            Arrays.fill(d, INF);
            Arrays.fill(p, -1);
            boolean[] visited = new boolean[n];
            d[s] = 0;
            // n time iterations
            for (int i = 0; i < n; i++) {
                // chosen vertex u
                int u = -1;
                for (int j = 0; j < n; j++) {
                    if (visited[j]) continue;
                    if (u == -1 || d[j] < d[u]) {
                        u = j;
                    }
                }
                if (d[u] == INF) break;
                visited[u] = true;
                // relaxations vertex u
                List<int[]> adjs = graph[u];
                for (int[] adj : adjs) {
                    int v = adj[0];
                    int weight = adj[1]; // len of (u -> v)
                    // relaxation edge u -> v
                    if (d[v] > d[u] + weight) {
                        // successful relaxation
                        d[v] = d[u] + weight;
                        p[v] = u;
                    }
                }
            }
        }

        /**
         * Check if p[] correct or not （最短路径信息正确性验证）
         */
        public boolean shortestPathInfoCorrectCheck(int s) {
            // is p[] all correct ?
            for (int i = 0; i < n; i++) {
                int cost = 0;
                int currentVertex = i;
                while (currentVertex != s) {
                    int pre = this.p[currentVertex];
                    if (pre == -1) {
                        cost = INF;
                        break;
                    }
                    cost += weights[pre][currentVertex];
                    currentVertex = pre;
                }
                if (cost != this.d[i]) {
                    // The shortest path was not correct!
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        SolutionDijkstra sol = new SolutionDijkstra();
        int n = 4;
        int[][] edges = {{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
        int distanceThreshold = 4; // 3
        System.out.println("test: " + sol.findTheCity(n, edges, distanceThreshold));
    }
}