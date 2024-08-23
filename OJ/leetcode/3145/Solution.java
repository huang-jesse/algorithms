import java.util.Arrays;

class Solution {
    private static final long MAX = (long)1e15;
    private long[] memo;
    public int[] findProductsOfElements(long[][] queries) {
        this.memo = new long[64];
        int n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            long from = queries[i][0];
            long to = queries[i][1];
            int mod = (int)queries[i][2];
            long left = binarySearch(from);
            long right = binarySearch(to + 1);
            long[] leftCounter = bitsCounter(left, from);
            long[] rightCounter = bitsCounter(right, to + 1);
            long res = 1L;
            boolean hasRes = false;
            for (int j = 0; j < 64; j++) {
                rightCounter[j] -= leftCounter[j];
                if (rightCounter[j] == 0) continue;
                res = (res * qpow((1L << j), rightCounter[j], mod)) % mod;
                hasRes = true;
            }
            if (!hasRes) res = 0;
            ans[i] = (int)res;
        }
        return ans;
    }

    private int qpow(long a, long n, int mod) {
        a = a % mod;
        int ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                // 当前二进制最后一位为1
                ans = (int)((long)ans * a % mod);
            }
            // a累乘
            a = (int)((long)a * a % mod);
            // n右移一位
            n = n >> 1;
        }
        return ans;
    }

    private long[] bitsCounter(long num, long totalBits) {
        long[] counter = new long[64];
        int upperBit = Long.SIZE - Long.numberOfLeadingZeros(num);
        for (int fixBit = 0; fixBit < upperBit; fixBit++) {
            Arrays.fill(memo, -1);
            counter[fixBit] = backtrack(memo, num, fixBit, upperBit - 1, true);
        }
        long bitsNum = getTotalBitCount(num);
        long removeBits = bitsNum - totalBits;
        for (int i = upperBit - 1; i >= 0 && removeBits > 0; i--) {
            if (((num >> i) & 1) == 0) continue;
            counter[i]--;
            removeBits--;
        }
        return counter;
    }

    private long binarySearch(long target) {
        long l = 0;
        long r = MAX;
        while (l < r) {
            long mid = l + ((r - l) >> 1);
            if (getTotalBitCount(mid) >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private long getTotalBitCount(long num) {
        long res = 0;
        int i = 0;
        for (long n = num >> i; n > 0; n >>= 1, i++) {
            res += (n / 2) << i;
            if (n % 2 > 0) {
                long mask = (1L << i) - 1;
                res += (num & mask) + 1;
            }
        }
        return res;
    }
    
    // private long getTotalBitCount(long num) {
    //     int upperBit = Long.SIZE - Long.numberOfLeadingZeros(num);
    //     long res = 0;
    //     for (int fixBit = 0; fixBit < upperBit; fixBit++) {
    //         Arrays.fill(memo, -1);
    //         res += backtrack(memo, num, fixBit, upperBit - 1, true);
    //     }
    //     return res;
    // }

    private long backtrack(long[] memo, long num, int fixBit, int index, boolean isLimit) {
        if (index == -1) return 1;
        if (!isLimit && memo[index] != -1) return memo[index];
        long res = 0;
        if (!isLimit || (((num >> index) & 1) == 1)) {
            // bit 1
            res += backtrack(memo, num, fixBit, index - 1, isLimit);
        }
        if (index != fixBit) {
            // bit 0
            res += backtrack(memo, num, fixBit, index - 1, isLimit && (((num >> index) & 1) == 0));
        }
        if (!isLimit) {
            memo[index] = res;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        long[][] queries = {{2,5,3}, {7,7,4}};
        // long[][] queries = {{1,3,7}};
        System.out.println("test: " + Arrays.toString(sol.findProductsOfElements(queries)));
    }
}