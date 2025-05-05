class Solution {
    private static final int MOD = (int)1e9 + 7;
    private static final int EVEN_DIGIT_COUNT = 5; // even indices {0,2,4,6,8}
    private static final int ODD_DIGIT_COUNT = 4; // odd indices {2,3,7,5}
    public int countGoodNumbers(long n) {
        int res = qpow(EVEN_DIGIT_COUNT * ODD_DIGIT_COUNT, n / 2);
        if (n % 2 == 1) {
            // last even indices
            res = (int)(((long)res * EVEN_DIGIT_COUNT) % MOD);
        }
        return res;
    }

    private static int qpow(long a, long n) {
        int ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = (int)((ans * a) % MOD);
            }
            a = a * a % MOD;
            n >>= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        long n = 4;
        System.out.println("test: " + sol.countGoodNumbers(n));
    }
}