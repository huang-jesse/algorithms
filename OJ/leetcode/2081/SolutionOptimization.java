class SolutionOptimization {
    private long[] digits;
    public long kMirror(int k, int n) {
        this.digits = new long[64]; // max long represent by base-2
        long ans = 0;
        long left = 1;
        while (n > 0) {
            long right = left * 10;
            // if op = 0, represent num of digits is odd, vise versa
            for (int op = 0; op < 2; op++) {
                for (long i = left; i < right && n > 0; i++) {
                    long num = i;
                    long remain = op == 0 ? i / 10 : i;
                    while (remain > 0) {
                        num = num * 10 + remain % 10;
                        remain /= 10;
                    }
                    if (check(num, k)) {
                        n--;
                        ans += num;
                    }
                }
            }
            left = right;
        }
        return ans;
    }

    private boolean check(long num, int base) {
        int length = 0;
        while (num > 0) {
            digits[length] = num % base;
            length++;
            num /= base;
        }
        int l = 0;
        int r = length - 1;
        while (l < r) {
            if (digits[l] != digits[r]) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int k = 3;
        int n = 7;// 499
        System.out.println("test: " + sol.kMirror(k, n));
    }
}