class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        long[][] preSum = new long[n+1][m+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long cur = (long) matrix[i][j];
                preSum[i+1][j+1] = preSum[i][j+1] + preSum[i+1][j] - preSum[i][j] + cur;
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int i1 = 0; i1 <= i; i1++) {
                    for (int j1 = 0; j1 <= j; j1++) {
                        long curSum = preSum[i+1][j+1] - (preSum[i+1][j1] + preSum[i1][j+1] - preSum[i1][j1]);
                        if (curSum == target) {
                            ans++;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[][] matrix = {{0,1,0},{1,1,1},{0,1,0}};
        int[][] matrix = {{1,-1}, {-1,1}};
        int target = 0;
        System.out.println("test: " + sol.numSubmatrixSumTarget(matrix, target));
    }
}