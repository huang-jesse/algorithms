class SolutionNew {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] isPalindromes = new boolean[n][n];
        // Palindrome
        for (int i = 0; i < 2 * n - 1; i++) {
            int leftIndex = i / 2;
            int rightIndex = i / 2 + i % 2;
            while (leftIndex >= 0 && rightIndex < n
                && s.charAt(leftIndex) == s.charAt(rightIndex)) {
                isPalindromes[leftIndex][rightIndex] = true;
                leftIndex--;
                rightIndex++;
            }
        }
        // dp
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - k + 1; j >= 0; j--) {
                if (isPalindromes[j][i]) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[j] + 1);
                    break;
                }
            }
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        SolutionNew sol = new SolutionNew();
        String s = "abaccdbbd";
        int k = 3;
        System.out.println("test: " + sol.maxPalindromes(s, k));
    }
}