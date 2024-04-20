import java.util.Arrays;

/**
 * https://leetcode.cn/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/solutions/2525946/dai-ni-fa-ming-floyd-suan-fa-cong-ji-yi-m8s51/
 */
class SolutionDFS {
    private static final int INF = (int)(1e4 + 1);
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] weights = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(weights[i], INF);
        }
        for (int[] edge : edges) {
            weights[edge[0]][edge[1]] = edge[2];
            weights[edge[1]][edge[0]] = edge[2];
        }
        // dfs(k,i,j)=min(dfs(k−1,i,j),dfs(k−1,i,k)+dfs(k−1,k,j))
        int[][][] memo = new int[n][n][n];
        int minCount = n;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (dfs(n - 1, i, j, weights, memo) <= distanceThreshold) {
                    count++;
                }
            }
            // 相等时取最大的 i
            if (count <= minCount) {
                minCount = count;
                ans = i;
            }
        }
        return ans;
    }

    private int dfs(int k, int i, int j, int[][] weights, int[][][] memo) {
        if (k < 0) {
            return weights[i][j];
        }
        if (memo[k][i][j] != 0) {
            return memo[k][i][j];
        }
        memo[k][i][j] = Math.min(dfs(k - 1, i, j, weights, memo), dfs(k - 1, i, k, weights, memo) + dfs(k - 1, k, j, weights, memo));
        return memo[k][i][j];
    }

    public static void main(String[] args) {
        SolutionDFS sol = new SolutionDFS();
        int n = 4;
        int[][] edges = {{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
        int distanceThreshold = 4;
        System.out.println("test: " + sol.findTheCity(n, edges, distanceThreshold));
    }
}