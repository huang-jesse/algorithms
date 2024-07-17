import java.util.Arrays;

class Solution {
    private static final int INF = 0x3fffffff;
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n; j >= 0; j--) {
                dp[j] = Math.min(dp[j], dp[Math.max(j - 1 - time[i], 0)] + cost[i]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] cost = {1,2,3,2};
        int[] time = {1,2,3,2};
        System.out.println("test: " + sol.paintWalls(cost, time));
    }
}