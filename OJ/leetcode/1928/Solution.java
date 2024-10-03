import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private static final int INF = 0x3fffffff;
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        // {node, time}
        List<int[]>[] graph = (ArrayList<int[]>[])new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            graph[edge[1]].add(new int[]{edge[0], edge[2]});
        }
        // minCost: dp[time][node]
        int[][] dp = new int[maxTime + 1][n];
        for (int i = 0; i <= maxTime; i++) Arrays.fill(dp[i], INF);
        dp[0][0] = passingFees[0];

        for (int i = 1; i <= maxTime; i++) {
            for (int j = 0; j < n; j++) {
                int cost = passingFees[j];
                for (int[] adj : graph[j]) {
                    int pre = adj[0];
                    int t = adj[1];
                    if (i - t < 0) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i - t][pre] + cost);
                }
            }
        }
        int ans = INF;
        for (int i = 0; i <= maxTime; i++) {
            ans = Math.min(ans, dp[i][n - 1]);
        }
        return ans == INF ? -1 : ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int maxTime = 29;
        int[][] edges = {{0,1,10},{1,2,10},{2,5,10},{0,3,1},{3,4,10},{4,5,15}};
        int[] passingFees = {5,1,2,20,20,3}; // res: 48
        System.out.println("test: " + sol.minCost(maxTime, edges, passingFees));
    }
}