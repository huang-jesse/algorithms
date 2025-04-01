class Solution {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[][] dp = new long[n][2];
        dp[n - 1][0] = 0;
        dp[n - 1][1] = questions[n - 1][0];
        for (int i = n - 2; i >= 0; i--) {
            dp[i][0] = Math.max(dp[i + 1][0], dp[i + 1][1]);
            int next = i + questions[i][1] + 1;
            if (next < n) {
                dp[i][1] = Math.max(dp[next][0], dp[next][1]) + questions[i][0];
            } else {
                dp[i][1] = questions[i][0];
            }
        }
        return Math.max(dp[0][0], dp[0][1]);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] questions = {{1,1},{2,2},{3,3},{4,4},{5,5}};
        System.out.println("test: " + sol.mostPoints(questions));
    }
}