import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final int INF = 0x3fffffff;
    public int maxSatisfaction(int[] satisfaction) {
        int n = satisfaction.length;
        List<Integer> positiveSatisfaction = new ArrayList<>();
        List<Integer> negativeSatisfaction = new ArrayList<>();
        for (int s : satisfaction) {
            if (s < 0) {
                negativeSatisfaction.add(s);
            } else {
                positiveSatisfaction.add(s);
            }
        }
        positiveSatisfaction.sort(null);
        negativeSatisfaction.sort(null);

        int ans = 0;
        int positiveSum = 0;
        int preDishes = 1;
        for (int s : positiveSatisfaction) {
            positiveSum += s;
            ans += preDishes * s;
            preDishes++;
        }
        int m = negativeSatisfaction.size();
        int[][] dp = new int[m + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -INF;
                dp[i][j] = -INF;
            }
        }
        for (int i = 0; i < m; i++) {
            int negativeSa = negativeSatisfaction.get(i);
            dp[i][0] = 0;
            for (int j = 0; j <= i; j++) {
                dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i][j + 1]);
                dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j] + positiveSum + negativeSa * (j + 1));
            }
        }
        int res = 0;
        for (int j = 0; j <= m; j++) {
            res = Math.max(res, dp[m][j]);
        }
        return ans + res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] satisfaction = {-1,-8,0,5,-9};
        System.out.println("test: " + sol.maxSatisfaction(satisfaction));
    }
}