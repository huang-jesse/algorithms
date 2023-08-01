class Solution {
    private final static int MOD = (int)(1e9 + 7);
    public int countSteppingNumbers(String low, String high) {
        int lowLen = low.length();
        int[] lowDigits = new int[lowLen];
        boolean isLowStepNum = true;
        for (int i = 0; i < lowLen; i++) {
            lowDigits[i] = low.charAt(i) - '0';
            if (i > 0 && Math.abs(lowDigits[i] - lowDigits[i - 1]) != 1) {
                isLowStepNum = false;
            }
        }
        int highLen = high.length();
        int[] highDigits = new int[highLen];
        for (int i = 0; i < highLen; i++) {
            highDigits[i] = high.charAt(i) - '0';
        }
        int ans = (count(highDigits) - count(lowDigits) + MOD) % MOD;
        if (isLowStepNum) {
            // 多减了 low 这个步进数字，要加回来
            ans = (ans + 1) % MOD;
        }
        return ans;
    }

    /**
     * 统计 [1, digits] 范围内的步进数字
     * @param digits
     * @return
     */
    private int count(int[] digits) {
        // 计算数字位数为 [1, digits.length - 1] 的结果
        int n = digits.length;
        int res = 0;
        for (int len = 1; len < n; len++) {
            int[][] dp = new int[len][10];
            for (int j = 1; j <= 9; j++) {
                dp[0][j] = 1;
            }
            for (int i = 1; i < len; i++) {
                for (int j = 0; j <= 9; j++) {
                    if (j > 0) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MOD;
                    }
                    if (j < 9) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MOD;
                    }
                }
            }
            for (int j = 0; j <= 9; j++) {
                res = (res + dp[len - 1][j]) % MOD;
            }
        }
        // 计算数字数位为 digits.length 的结果
        // 标记 digits 数字本身是否为步进数字
        boolean isStepNum = true;
        int[][] dp = new int[n][10];
        for (int j = 1; j < digits[0]; j++) {
            // [0...digits[0]-1] [digits[0]...9] 都初始设置为0
            dp[0][j] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j > 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MOD;
                }
                if (j < 9) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MOD;
                }
                // 统计，当之前数位选取原数字本身的数位时，当前i数位选取j，此时的数字，是否是步进数字。
                if (isStepNum && j < digits[i] && Math.abs(j - digits[i - 1]) == 1) {
                    dp[i][j]++;
                }
            }
            if (Math.abs(digits[i] - digits[i - 1]) != 1) {
                isStepNum = false;
            }
        }
        for (int j = 0; j <= 9; j++) {
            res = (res + dp[n - 1][j]) % MOD;
        }
        // 如果该数字就是步进数字
        if (isStepNum) {
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String low = "1";
        String high = "11";
        System.out.println("test: " + sol.countSteppingNumbers(low, high));
    }
}