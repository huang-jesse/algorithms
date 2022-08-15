class Solution {
    public long countSubMeritc(int n, int m, int k, int[][] metric) {
        int[][] preSum = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                preSum[i][j] = preSum[i][j-1] + preSum[i-1][j] - preSum[i-1][j-1] + metric[i-1][j-1];
            }
        }

        long ans = 0;
        // 枚举矩阵上下边
        for (int x1 = 1; x1 <= n; x1++) {
            for (int x2 = x1; x2 <= n; x2++) {
                int l = 1;
                int r = 1;
                while (r <= m) {
                    while (l <= r && !check(preSum, k, x1, l, x2, r)) {
                        l++;
                    }
                    ans += r - l + 1;
                    r++;
                }
            }
        }
        return ans;
    }

    private static boolean check(int[][] preSum, int k, int x1, int y1, int x2, int y2) {
        return preSum[x2][y2] - preSum[x2][y1-1] - preSum[x1-1][y2] + preSum[x1-1][y1-1] <= k;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        int m = 4;
        int k = 10;
        int[][] metric = {{1,2,3,4},
                          {5,6,7,8},
                          {9,10,11,12}};
        System.out.println("test: " + sol.countSubMeritc(n, m, k, metric));
    }
}