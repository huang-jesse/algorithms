class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] isPalindromes = new boolean[n][n];
        // Odd character palindrome
        for (int i = 0; i < n; i++) {
            int leftIndex = i;
            int rightIndex = i;
            while (leftIndex >= 0 && rightIndex < n
                && s.charAt(leftIndex) == s.charAt(rightIndex)) {
                isPalindromes[leftIndex][rightIndex] = true;
                leftIndex--;
                rightIndex++;
            }
        }
        // Even character palindrome
        for (int i = 0; i + 1 < n; i++) {
            int leftIndex = i;
            int rightIndex = i + 1;
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
        Solution sol = new Solution();
        String s = "abaccdbbd";
        int k = 3;
        System.out.println("test: " + sol.maxPalindromes(s, k));
    }
}