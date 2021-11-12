class Solution {
    private static int[][] memo = new int[210][210];
    public int getMoneyAmount(int n) {
        return dfs(1, n);
    }

    private int dfs(int low, int high) {
        if (low >= high) return 0;
        if (memo[low][high] != 0) {
            return memo[low][high];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = low; i <= high; i++) {
            int money = Math.max(dfs(low, i-1), dfs(i+1, high)) + i;
            ans = Math.min(ans, money);
        }
        memo[low][high] = ans;
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 200;
        System.out.println("test: " + sol.getMoneyAmount(n));
    }
}