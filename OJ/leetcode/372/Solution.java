class Solution {
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
        return pow(dfs(a, b, index-1), 10) * pow(a, b[index]) % MOD;
    }

    private int pow(int a, int b) {
        int ans = 1;
        while (b > 0) {
            ans = ans * a % MOD;
            b--;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int a = 1;
        int[] b = {4,3,3,8,5,2};
        System.out.println("test: " + sol.superPow(a, b));
    }
}