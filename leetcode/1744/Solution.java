import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = candiesCount.length;
        int m = queries.length;
        long[] preSum = new long[n+1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i-1] + candiesCount[i-1];
        }
        boolean[] ans = new boolean[m];
        for (int i = 0; i < m; i++) {
            int favoriteType = queries[i][0];
            int favoriteDay = queries[i][1];
            int dailyCap = queries[i][2];
            long maxCandies = preSum[favoriteType+1];
            long minCandies = preSum[favoriteType] + 1;
            long maxLimit = (long)(favoriteDay+1) * dailyCap;
            long minLimit = (long)favoriteDay + 1;
            boolean isMinLimitedOk = maxCandies >= minLimit;
            boolean isMaxLimitedOk = maxLimit >= minCandies;
            if (isMinLimitedOk && isMaxLimitedOk) {
                ans[i] = true;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] candiesCount = {7,4,5,3,8};
        int[][] queries = {{0,2,2},{4,2,4},{2,13,1000000000}};
        boolean[] ans = sol.canEat(candiesCount, queries);
        List<Boolean> list = new ArrayList<>();
        for (boolean a : ans) {
            list.add(a);
        }
        System.out.println("test: " + list);
    }
}