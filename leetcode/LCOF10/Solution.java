class Solution {
    private static final int MOD = (int)1e9 + 7;
    public int fib(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        int pre = 0;
        int cur = 1;
        int index = 2;
        while (index <= n) {
            index++;
            int temp = cur;
            cur = (pre + cur) % MOD;
            pre = temp;
        }
        return cur;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 10;
        System.out.println("test: " + sol.fib(n));
    }
}