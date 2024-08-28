import java.util.Arrays;

class Solution {
    private static final int INF = 1001;
    private int[][] counter;
    private int[][] memo;
    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        this.counter = new int[n + 1][26];
        for (int i = 0; i < n; i++) {
            int charVal = s.charAt(i) - 'a';
            for (int j = 0; j < 26; j++) {
                this.counter[i + 1][j] = this.counter[i][j];
            }
            this.counter[i + 1][charVal]++;
        }
        this.memo = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(this.memo[i], -1);
        }

        return backtrack(s, -1, 0);
    }

    private int backtrack(String s, int pre, int cur) {
        int n = s.length();
        if (cur == n) {
            return isBalanced(pre, cur - 1) ? 1 : INF;
        }
        boolean isBalanced = isBalanced(pre, cur);
        if (this.memo[pre + 1][cur + 1] != -1) return this.memo[pre + 1][cur + 1];
        int res = INF;
        if (isBalanced) {
            res = 1 + backtrack(s, cur, cur + 1);
        }
        res = Math.min(res, backtrack(s, pre, cur + 1));
        this.memo[pre + 1][cur + 1] = res;
        return res;
    }

    private boolean isBalanced(int pre, int cur) {
        if (pre == cur) return true;
        int last = 0;
        for (int i = 0; i < 26; i++) {
            int count = this.counter[cur + 1][i] - this.counter[pre + 1][i];
            if (count != 0) {
                if (last == 0) {
                    last = count;
                } else if (last != count) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String s = "fabccddg"; // res: 3
        String s = "abababaccddb"; // res: 2
        System.out.println("test: " + sol.minimumSubstringsInPartition(s));
    }
}