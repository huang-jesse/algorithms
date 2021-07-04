class Solution {
    static final int MOD = (int)1e9 + 7;
    public int countGoodNumbers(long n) {
        int prime = 4;
        int even = 5;
        int mul = prime * even;
        int ans = 1;
        if (n % 2 == 0) {
            ans = qpow(mul, n/2, MOD);
        } else {
            ans = qpow(mul, n/2, MOD);
            ans = (int)((long)ans * even % MOD);
        }
        return ans;
    }

    private static int qpow(long a, long n, int mod) {
        int ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = (int)((ans * a) % mod);
            }
            a = a * a % mod;
            n >>= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        long n = 17;
        System.out.println("test: " + sol.countGoodNumbers(n));
    }
}