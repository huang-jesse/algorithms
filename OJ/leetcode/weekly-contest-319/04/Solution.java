class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] isPalidrome = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            int leftIndex = i;
            int rightIndex = i;
            while (leftIndex >= 0 && rightIndex < n) {
                if (s.charAt(leftIndex) != s.charAt(rightIndex)) {
                    break;
                }
                isPalidrome[leftIndex][rightIndex] = true;
                leftIndex--;
                rightIndex++;
            }
        }

        for (int i = 0; i + 1 < n; i++) {
            int leftIndex = i;
            int rightIndex = i + 1;
            while (leftIndex >= 0 && rightIndex < n) {
                if (s.charAt(leftIndex) != s.charAt(rightIndex)) {
                    break;
                }
                isPalidrome[leftIndex][rightIndex] = true;
                leftIndex--;
                rightIndex++;
            }
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - k + 1; j >= 0; j--) {
                if (isPalidrome[j][i]) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[j] + 1);
                }
            }
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "fttfjofpnpfydwdwdnns";// 4
        int k = 2;
        // String s = "abaccdbbd";// 2
        // int k = 3;
        System.out.println("test: " + sol.maxPalindromes(s, k));
    }
}