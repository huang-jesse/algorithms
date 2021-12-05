class SolutionQpow {
    private static int MOD = 1337;
    public int superPow(int a, int[] b) {
        a = a % MOD;
        if (a <= 1)
            return a;
        return dfs(a, b, b.length-1);
    }

    private int dfs(int a, int[] b, int index) {
        if (index < 0)
            return 1;
        return qpow(dfs(a, b, index-1), 10) * qpow(a, b[index]) % MOD;
    }

    // private int qpow(int a, int n) {
    //     if (n == 0) {
    //         return 1;
    //     } else if (n % 2 == 1) {
    //         // odd
    //         return qpow(a, n-1) * a % MOD;
    //     } else {
    //         // even
    //         int temp = qpow(a, n/2);
    //         return temp * temp % MOD;
    //     }
    // }

    private int qpow(int a, int n) {
        int ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                // 当前二进制最后一位为1
                ans = ans * a % MOD;
            }
            // a累乘
            a = a * a % MOD;
            // n右移一位
            n = n >> 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionQpow sol = new SolutionQpow();
        int a = 1;
        int[] b = {4,3,3,8,5,2};
        System.out.println("test: " + sol.superPow(a, b));
    }
}