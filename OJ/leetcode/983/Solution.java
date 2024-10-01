import java.util.Arrays;

class Solution {
    private static final int INF = 0x3fffffff;
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            // day-1
            int target = days[i] - 1;
            int pre = binarySearch(days, target);
            dp[i + 1] = Math.min(dp[i + 1], dp[pre + 1] + costs[0]);
            // day-7
            target = days[i] - 7;
            pre = binarySearch(days, target);
            dp[i + 1] = Math.min(dp[i + 1], dp[pre + 1] + costs[1]);
            // day-30
            target = days[i] - 30;
            pre = binarySearch(days, target);
            dp[i + 1] = Math.min(dp[i + 1], dp[pre + 1] + costs[2]);
        }
        return dp[n];
    }

    private int binarySearch(int[] days, int target) {
        if (days[0] > target) return -1;
        int l = 0;
        int r = days.length - 1;
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            if (days[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] days = {1,4,6,7,8,20};
        int[] costs = {2,7,15};
        // int[] days = {1,3,7};
        // int[] costs = {1,4,20};
        System.out.println("test: " + sol.mincostTickets(days, costs));
    }
}