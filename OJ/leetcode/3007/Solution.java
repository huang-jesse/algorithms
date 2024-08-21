import java.util.Arrays;

class Solution {
    public long findMaximumNumber(long k, int x) {
        int bitLimit = Long.SIZE - Long.numberOfLeadingZeros(k - 1) + 2;
        bitLimit = Math.max(bitLimit, x + 2);
        long maxNum = (1L << bitLimit);
        long l = 0L;
        long r = maxNum;
        while (l < r) {
            long mid = l + ((r - l + 1) >> 1);
            long price = getPrice(mid, x);
            if (price <= k) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private long getPrice(long num, int x) {
        int upperBit = Long.SIZE - Long.numberOfLeadingZeros(num);
        long[] memo = new long[upperBit];
        long res = 0;
        for (int fixBit = x - 1; fixBit < upperBit; fixBit += x) {
            Arrays.fill(memo, -1);
            res += backtrack(memo, num, fixBit, upperBit - 1, true);
        }
        return res;
    }

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
        long k = 7;
        int x = 2; // res: 9
        System.out.println("test: " + sol.findMaximumNumber(k, x));
    }
}