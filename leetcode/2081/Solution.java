class Solution {
    // private long[]
    private int n;
    public long kMirror(int k, int n) {
        this.n = n;
        long ans = 0;
        int d = 1;
        while (this.n > 0) {
            ans += permutation(k, d, 0, 0);
            d++;
        }
        return ans;
    }

    private long permutation(int base, int d, int index, long pre) {
        int half = (d + 1) / 2;
        long res = 0;
        if (index >= half) {
            // is a permutation
            if (check(pre, base)) {
                // valid permutation
                this.n--;
                res = pre;
            }
            return res;
        }

        boolean isEnd = index == half - 1;
        boolean isEven = d % 2 == 0;
        long leftPower = qpow(10, d - 1 - index);
        long rightPower = qpow(10, index);
        int startDigit = index == 0 ? 1 : 0;
        for (int digit = startDigit; digit < 10; digit++) {
            long current = pre + digit * leftPower;
            // when d is odd and index is half - 1, don't add right digit
            if (isEven || !isEnd) {
                // palindromic num, add right digit to current
                current += digit * rightPower;
            }
            res += permutation(base, d, index + 1, current);
            if (this.n == 0) return res;
        }
        return res;
    }

    private boolean check(long num, int base) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % base);
            num /= base;
        }
        int l = 0;
        int r = sb.length() - 1;
        while (l < r) {
            if (sb.charAt(l) != sb.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    private long qpow(long a, int n) {
        long ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                // 当前二进制最后一位为1
                ans = ans * a;
            }
            // a累乘
            a = a * a;
            // n右移一位
            n = n >> 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int k = 3;
        int n = 7;// 499
        System.out.println("test: " + sol.kMirror(k, n));
    }
}