import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private int[][] distanceCoinsCounter;
    private int[] coins;
    private List<Integer>[] graph;
    private int[] dp;
    private int[] nodeCollectTimes;
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        this.distanceCoinsCounter = new int[3][n];
        this.nodeCollectTimes = new int[n];
        this.dp = new int[n];
        this.coins = coins;
        this.graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        // dfs 计算以 0 为 根节点时，收集 coin 需要的次数
        int rootRes = dfs(-1, 0);
        dp[0] = rootRes;

        // 换根 DP
        dp(0, -1, distanceCoinsCounter[1][0], distanceCoinsCounter[2][0]);

        return Arrays.stream(dp).min().getAsInt();
    }

    /**
     * 换根 DP
     * @param root
     * @param preDistance2Count
     */
    private void dp(int root, int pre, int rootDistance1Count, int rootDistance2Count) {
        int rootCollectTimes = dp[root];
        for (int current : graph[root]) {
            if (current == pre) {
                continue;
            }
            // 以 current 为根，进行计算
            int currentSideCollectTimes = nodeCollectTimes[current];
            int rootSideCollectTimes = rootCollectTimes;
            if (distanceCoinsCounter[2][current] > 0) {
                rootSideCollectTimes -= (2 + currentSideCollectTimes);
            }
            // 以 current 为根，收集所有节点的 coin 需要的次数
            int res = currentSideCollectTimes;
            int rootSideDistance1Count = rootDistance1Count - coins[current];
            // 用 root 节点减去当前 current 节点的的距离大于等于 1 和 2 的 coin 节点数，等于远离当前 current 节点一侧的距离大于等于 2 的节点数
            int rootSideDistance2Count = rootDistance2Count - distanceCoinsCounter[1][current] - distanceCoinsCounter[2][current];
            if (rootSideDistance2Count > 0) {
                // 以 current 为根节点，需要来回共 2 次，才能收集 root 的 coin
                res += 2 + rootSideCollectTimes;
            }
            dp[current] = res;
            // 当前节点距离为 1 的所有 coin 数
            int currentDistance1Count = distanceCoinsCounter[1][current] + coins[root];
            // 当前节点距离大于等于 2 的所有 coin 数
            int currentDistance2Count = distanceCoinsCounter[2][current] + rootSideDistance1Count + rootSideDistance2Count;
            // 继续换根子节点
            dp(current, root, currentDistance1Count, currentDistance2Count);
        }
    }

    private int dfs(int pre, int current) {
        int ans = 0;
        distanceCoinsCounter[0][current] = coins[current];
        for (int next : graph[current]) {
            if (next == pre) {
                continue;
            }
            int res = dfs(current, next);
            // 当 next 的子节点存在 2 以上的距离的 coin 时才需要主动去 next 收集（否则就在当前 current 收集范围内)
            if (distanceCoinsCounter[2][next] > 0) {
                // 需要来回共 2 次，才能收集 next 的 coin
                ans += 2 + res;
            }
            // 统计距离为 1 的 coin
            distanceCoinsCounter[1][current] += coins[next];
            // 统计距离大于等于 2 的 coin
            distanceCoinsCounter[2][current] += distanceCoinsCounter[1][next] + distanceCoinsCounter[2][next];
        }
        // 以 0 为根节点时，当前节点收集子节点 coin 需要的次数
        nodeCollectTimes[current] = ans;
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] coins = {0,0,0,1,1,0,0,1};
        // int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{5,6},{5,7}};
        int[] coins = {1,0,0,0,0,1};
        int[][] edges = {{0,1},{1,2},{2,3},{3,4},{4,5}};
        System.out.println("test: " + sol.collectTheCoins(coins, edges));
    }
}