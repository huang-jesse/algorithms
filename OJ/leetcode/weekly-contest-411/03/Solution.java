import java.util.Arrays;

/**
 * 参考
 * https://leetcode.cn/problems/find-the-largest-palindrome-divisible-by-k/solutions/2884468/tan-xin-dp-by-tsreaper-3odq/
 * https://leetcode.cn/problems/find-the-largest-palindrome-divisible-by-k/solutions/2884802/100409tan-xin-dp-by-rinors-b-rsgl/
 */
class Solution {
    public String largestPalindrome(int n, int k) {
        int[] p = new int[n];
        p[0] = 1;
        for (int i = 1; i < n; i++) p[i] = (p[i - 1] * 10) % k;

        int limit = (n + 1) / 2 + 1;
        // dp[i][j] 表示，只考虑枚举字符串的 s[i],s[i+1],...,s[n-1-i] 这段，
        // 当前段求和后，对k取模的结果为j时，此时s[i]的最大可能取值数字。
        int[][] dp = new int[limit][k];
        for (int i = 0; i < limit; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[limit - 1][0] = 0;
        for (int i = (n - 1) / 2; i >= 0; i--) {
            // t 当 n 为奇数且 i 为中间值时取 p[i] 其他情况均取 p[i] + p[n - 1 - i]
            int t = i == (n - 1 - i) ? p[i] : p[i] + p[n - 1 - i];
            for (int j = 0; j < k; j++) {
                // 取尽可能大的数位 d
                for (int d = 9; d >= 0; d--) {
                    int prej = (j - (t * d) % k + k) % k;
                    if (dp[i + 1][prej] != -1) {
                        dp[i][j] = d;
                        break;
                    }
                }
            }
        }

        // 构造回文字符串
        char[] chars = new char[n];
        for (int i = 0, j = 0; i <= (n - 1) / 2; i++) {
            int d = dp[i][j];
            chars[i] = (char)('0' + d);
            int t = i == (n - 1 - i) ? p[i] : p[i] + p[n - 1 - i];
            j = (j - (t * d) % k + k) % k;
        }
        for (int i = (n - 1) / 2 + 1; i < n; i++) {
            chars[i] = chars[n - 1 - i];
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int n = 5;
        // int k = 6; // res:89898
        int n = 1;
        int k = 4; // res:8
        // int n = 3;
        // int k = 5; // res:595
        System.out.println("test: " + sol.largestPalindrome(n, k));
    }
}