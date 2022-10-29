class SolutionDigitDP {
    public int atMostNGivenDigitSet(String[] digits, int n) {
        char[] numDigits = String.valueOf(n).toCharArray();
        int m = numDigits.length;
        int k = digits.length;
        // dp[i][0] 表示由 digits 构成且小于 n 的前 i 位的数字的个数
        // dp[i][1] 表示由 digits 构成且等于 n 的前 i 位的数字的个数，可知 dp[i][1] 的取值只能为 0 和 1
        int[][] dp = new int[m + 1][2];
        dp[0][1] = 1;
        for (int i = 1; i <= m; i++) {
            char currentDigit = numDigits[i - 1];
            if (i > 1) {
                dp[i][0] = k + k * dp[i - 1][0];
            }
            for (int j = 0; j < k; j++) {
                if (digits[j].charAt(0) < currentDigit) {
                    dp[i][0] += dp[i - 1][1];
                } else if (digits[j].charAt(0) == currentDigit) {
                    dp[i][1] = dp[i - 1][1];
                } else {
                    // digits[j].charAt(0) > currentDigit
                    break;
                }
            }
        }
        return dp[m][0] + dp[m][1];
    }

    public static void main(String[] args) {
        SolutionDigitDP sol = new SolutionDigitDP();
        // String[] digits = {"1","3","5","7"};
        // int n = 100; // ans = 20
        String[] digits = {"1","4","9"};
        int n = 1000000000; // ans = 29523
        System.out.println("test: " + sol.atMostNGivenDigitSet(digits, n));
    }
}