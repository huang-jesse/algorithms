import java.util.Arrays;

class SolutionFloyd {
    private static final int INF = 0x3fffffff;
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        // {nodei, {nodej, weight}}
        int[][] edges = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(edges[i], INF);
        for (int[] road : roads) {
            edges[road[0]][road[1]] = Math.min(edges[road[0]][road[1]], road[2]);
            edges[road[1]][road[0]] = Math.min(edges[road[1]][road[0]], road[2]);
        }
        int ans = 0;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], INF);
        // mask represent vertices that existed
        outer:
        for (int mask = 0; mask < (1 << n); mask++) {
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 0) {
                    Arrays.fill(f[i], INF);
                };
                // add edges
                for (int j = 0; j < n; j++) {
                    if (((mask >> j) & 1) == 0) {
                        f[i][j] = INF;
                    } else {
                        // add edge(i,j)
                        f[i][j] = edges[i][j];
                    }
                }
            }
            // Floyd algorithm
            for (int k = 0; k < n; k++) {
                if (((mask >> k) & 1) == 0) continue;
                for (int i = 0; i < n; i++) {
                    if (((mask >> i) & 1) == 0) continue;
                    for (int j = 0; j < n; j++) {
                        if (((mask >> j) & 1) == 0) continue;
                        f[i][j] = Math.min(f[i][j], f[i][k] + f[k][j]);
                    }
                }
            }
            // check distance
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 0) continue;
                for (int j = i + 1; j < n; j++) {
                    if (((mask >> j) & 1) == 0) continue;
                    // invlid
                    if (f[i][j] > maxDistance) continue outer;
                }
            }
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionFloyd sol = new SolutionFloyd();
        int n = 3;
        int maxDistance = 12;
        // int[][] roads = {{0,1,2},{1,2,10},{0,2,10}}; // 5
        // int[][] roads = {{0,1,20},{0,1,10},{1,2,2},{0,2,2}}; // 7
        int[][] roads = {{1,0,11},{1,0,16},{0,2,13}}; // 5
        System.out.println("test: " + sol.numberOfSets(n, maxDistance, roads));
    }
}