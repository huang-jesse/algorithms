import java.util.Arrays;

class SolutioDP {
    static final int INF = (int)Math.pow(10, 6) + 1;
    static final int HOUSE_WITHOUT_COLOT = -1;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        // make assume
        // house: 0 ~ m-1
        // color: 0 ~ n-1
        // street: 0 ~ target-1
        int[][][] dp = new int[m][n][target];
        // minus all colors seq, like 0 -> -1, 1 -> 0, then color: 0 ~ n, -1 present this is without color.
        for (int i = 0; i < m; i++) {
            --houses[i];
        }
        // initialize status
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        // begin dp
        dpProcess(m, n, target, houses, cost, dp);
        // find answer from all colors
        int ans = INF;
        for (int j = 0; j < n; ++j) {
            ans = Math.min(ans, dp[m - 1][j][target - 1]);
        }
        return ans == INF ? -1 : ans;
    }

    private void dpProcess(int m, int n, int target, int[] houses, int[][] cost, int[][][]dp) {
        // do dp process
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (houses[i] != HOUSE_WITHOUT_COLOT && houses[i] != j) {
                    continue;
                }
                for (int k = 0; k < target; k++) {
                    int temp = INF;
                    for (int p = 0; p < n; p++) {
                        if (j == p) {
                            if (i == 0) {
                                if (k == 0) {
                                    temp = 0;
                                }
                            } else {
                                temp = Math.min(temp, dp[i-1][j][k]);
                            }
                        } else {
                            if (i > 0 && k > 0) {
                                temp = Math.min(temp, dp[i-1][p][k-1]);
                            }
                        }
                    }
                    if (temp != INF && houses[i] == HOUSE_WITHOUT_COLOT) {
                        dp[i][j][k] = temp + cost[i][j];
                    } else {
                        dp[i][j][k] = temp;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SolutioDP sol = new SolutioDP();
        int[] houses = new int[]{0,2,1,2,0};
        // int[] houses = new int[]{0,0,0,0,0};
        int[][] cost = new int[][] {{1,10},{10,1},{10,1},{1,10},{5,1}};
        int m = 5;
        int n = 2;
        int target = 3;
        System.out.println("test: " + sol.minCost(houses, cost, m, n, target));
    }
}