class SolutionOptimization {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            int next = Math.min(n, i + questions[i][1] + 1);
            dp[i] = Math.max(dp[i + 1], dp[next] + questions[i][0]);
        }
        return dp[0];
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[][] questions = {{1,1},{2,2},{3,3},{4,4},{5,5}};
        System.out.println("test: " + sol.mostPoints(questions));
    }
}