import java.util.Arrays;

class Solution {
    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int[] counter = new int[26];
            for (int j = i - 1; j >= 0; j--) {
                counter[s.charAt(j) - 'a']++;
                if (isValid(counter)) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n];
    }

    private boolean isValid(int[] counter) {
        int last = -1;
        for (int count : counter) {
            if (count == 0) continue;
            if (last == -1) {
                last = count;
            }
            if (last != count) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "fabccddg";
        System.out.println("test: " + sol.minimumSubstringsInPartition(s));
    }
}