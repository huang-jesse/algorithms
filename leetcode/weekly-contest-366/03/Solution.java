import java.util.ArrayList;
import java.util.List;

class Solution {
    public int minOperations(String s1, String s2, int x) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int n = s1.length();
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (chars1[i] != chars2[i]) {
                indexes.add(i);
            }
        }
        int diffCnt = indexes.size();
        if (diffCnt % 2 != 0) {
            // 奇数项不同，不能相等
            return -1;
        }
        if (diffCnt == 0) {
            return 0;
        }

        int[] dp = new int[diffCnt + 1];
        dp[1] = 0;
        for (int i = 2; i <= diffCnt; i++) {
            if (i % 2 == 0) {
                // 目前考虑的元素个数为偶数，使用第一种操作，删除当前项，同时删除之前未删除的某项
                dp[i] = dp[i - 1] + x;
            } else {
                // 目前考虑的元素个数为奇数，不删除当前项
                dp[i] = dp[i - 1];
            }
            // 考虑使用第二种操作
            dp[i] = Math.min(dp[i], dp[i - 2] + (indexes.get(i - 1) - indexes.get(i - 2)));
        }
        return dp[diffCnt];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String s1 = "1100011000";
        // String s2 = "0101001010";
        // int x = 2;
        String s1 = "00010";
        String s2 = "00010";
        int x = 2;
        System.out.println("test: " + sol.minOperations(s1, s2, x));
    }
}