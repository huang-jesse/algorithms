class Solution {
    private static final int INF = 20;
    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length;
        int m = 1 << n;
        // 将每个任务 task[i] 看作某个 「 十进制数字 」 的二进制上的一位，1 表示该任务被选择，反之不被选择。
        int[] dp = new int[m];
        // 0表示所有任务都未完成，为无效状态
        dp[0] = INF;
        // 预处理所有状态，能够在sessionTime内完成的状态置为1，否则置为INF
        for (int i = 1; i < m; i++) {
            if (isOneSessionCompelete(tasks, sessionTime, i)) {
                dp[i] = 1;
            } else {
                dp[i] = INF;
            }
        }

        // 枚举所有状态
        for (int i = 1; i < m; i++) {
            if (dp[i] != INF)
                continue;
            // 枚举当前状态的所有二进制子集
            for (int j = i; j > 0; j = (j-1) & i) {
                // i 状态的最优解可能由当前子集 j 与子集 j 的补集得来
                dp[i] = Math.min(dp[i], dp[j] + dp[i ^ j]);
            }
        }
        return dp[m-1];
    }

    /**
     * 当前状态的所有任务是否能够在sessionTime内完成
     * @param tasks
     * @param sessionTime
     * @param state
     * @return
     */
    private boolean isOneSessionCompelete(int[] tasks, int sessionTime, int state) {
        int idx = 0;
        int spend = 0;
        while (state > 0) {
            int bit = state & 1;
            if (bit == 1) {
                spend += tasks[idx];
            }
            state = state >> 1;
            idx++;
        }
        return spend <= sessionTime;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] tasks = {2,3,3,4,4,4,5,6,7,10};
        int sessionTime = 12;
        System.out.println("test: " + sol.minSessions(tasks, sessionTime));
    }
}