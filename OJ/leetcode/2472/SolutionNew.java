class SolutionNew {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        for (int num = 0; num < 2 * n - 1; num++) {
            int l = num / 2;
            int r = num / 2 + num % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                isPalindrome[l][r] = true;
                l--;
                r++;
            }
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - k + 1; j >= 0; j--) {
                if (isPalindrome[j][i]) {
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