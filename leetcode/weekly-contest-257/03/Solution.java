class Solution {
    private static final int MOD = (int)1e9 + 7;
    /**
     * 动态规划
     * 参考题解：https://leetcode-cn.com/problems/first-day-where-you-have-been-in-all-the-rooms/solution/qian-zhui-he-you-hua-dp-by-endlesscheng-j10b/
     * @param nextVisit
     * @return
     */
    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int n = nextVisit.length;
        int[] dp = new int[n+1];
        int[] preSum = new int[n+1];
        dp[1] = 1;
        preSum[1] = dp[1];
        for (int i = 1; i < n-1; i++) {
            int j = nextVisit[i];
            // dp[i] = i-j+1 + ∑(k=j->i−1)dp[k]
            dp[i+1] = (i-j+1 + preSum[i] - preSum[j] + MOD) % MOD;
            preSum[i+1] = (preSum[i] + dp[i+1]) % MOD;
        }
        return (preSum[n-1] + n - 1) % MOD;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nextVisit = {0,0};
        // int[] nextVisit = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        System.out.println("test: " + sol.firstDayBeenInAllRooms(nextVisit));
    }
}