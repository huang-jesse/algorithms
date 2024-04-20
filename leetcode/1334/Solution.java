import java.util.Arrays;

/**
 * https://leetcode.cn/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/solutions/2525946/dai-ni-fa-ming-floyd-suan-fa-cong-ji-yi-m8s51/
 */
class Solution {
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
        // dp(k,i,j)=min(dp(k−1,i,j),dp(k−1,i,k)+dp(k−1,k,j))
        int[][] dp = new int[n][n];
        dp = weights;
        for (int k = 0; k < n; k++) {
            int[][] temp = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        continue;
                    }
                    temp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
            dp = temp;
        }
        int minCount = n;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (dp[i][j] <= distanceThreshold) {
                    count++;
                }
            }
            if (count <= minCount) {
                minCount = count;
                ans = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 4;
        int[][] edges = {{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
        int distanceThreshold = 4;
        System.out.println("test: " + sol.findTheCity(n, edges, distanceThreshold));
    }
}