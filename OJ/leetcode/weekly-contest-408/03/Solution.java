class Solution {
    private static final char ZERO = '0';
    public int numberOfSubstrings(String s) {
        int n = s.length();
        // next zero index
        int[] next = new int[n + 1];
        next[n] = n;
        for (int i = n - 1; i >= 0; i--) {
            next[i] = next[i + 1];
            if (s.charAt(i) == ZERO) {
                next[i] = i;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int totalLen = n - i + 1;
            int zeroCount = s.charAt(i) == ZERO ? 1 : 0;
            for (int j = i; j < n; j = next[j + 1], zeroCount++) {
                if (zeroCount * zeroCount > (totalLen - zeroCount)) break;
                int oneCount = (next[j + 1] - i) - zeroCount;
                if (zeroCount * zeroCount > oneCount) continue;
                int c = oneCount - zeroCount * zeroCount;
                ans += Math.min(c + 1, next[j + 1] - j);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "101101"; // 16
        System.out.println("test: " + sol.numberOfSubstrings(s));
    }
}