import java.util.Arrays;

class Solution {
    private long[] memo;
    private int limit;
    private int n; // len of highNum
    private char[] lowNum;
    private char[] highNum;
    private char[] s;

    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        String low = Long.toString(start);
        String high = Long.toString(finish);
        this.n = high.length();
        low = "0".repeat(n - low.length()).concat(low);
        this.memo = new long[n];
        Arrays.fill(this.memo, -1);
        this.limit = limit;
        this.lowNum = low.toCharArray();
        this.highNum = high.toCharArray();
        this.s = s.toCharArray();
        return backtrack(0, true, true);
    }

    private long backtrack(int i, boolean limitLow, boolean limitHigh) {
        if (i == n) return 1;
        if (!limitLow && !limitHigh && this.memo[i] != -1) {
            return this.memo[i];
        }
        int lo = limitLow ? lowNum[i] - '0' : 0;
        int hi = limitHigh ? highNum[i] - '0' : 9;
        long res = 0;
        if (i < (n - s.length)) {
            // 枚举数位
            for (int d = lo; d <= Math.min(hi, limit); d++) {
                res += backtrack(i + 1, limitLow && d == lo, limitHigh && d == hi);
            }
        } else {
            // 该数位只能填 s[i-diff]
            int d = s[i - (highNum.length - s.length)] - '0';
            if (lo <= d && d <= Math.min(hi, limit)) {
                res = backtrack(i + 1, limitLow && d == lo, limitHigh && d == hi);
            }
        }
        if (!limitLow && !limitHigh) {
            this.memo[i] = res;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        long start = 1;
        long finish = 6000;
        int limit = 4;
        String s = "124";
        System.out.println("test: " + sol.numberOfPowerfulInt(start, finish, limit, s));
    }
}