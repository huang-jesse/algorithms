class Solution {
    private double[][] memo;
    private int[][] dirs = {{-2, -1}, {-2, 1}
                           ,{2, -1}, {2, 1}
                           ,{-1, -2}, {1, -2}
                           ,{-1, 2}, {1, 2}};
    public double knightProbability(int n, int k, int row, int column) {
        memo = new double[n*n][k+1];
        int positionCode = encodePosition(row, column, n);
        return dfs(n, k, positionCode);
    }

    private double dfs(int n, int k, int positionCode) {
        if (k == 0) {
            return 1.0;
        }
        if (memo[positionCode][k] != 0.0) {
            return memo[positionCode][k];
        }
        double prob = 0.0;
        int[] position = decodePosition(positionCode, n);
        // next
        for (int[] dir : dirs) {
            int[] nextPosition = new int[]{position[0]+dir[0], position[1]+dir[1]};
            if (nextPosition[0] < 0 || nextPosition[0] >= n
            || nextPosition[1] < 0 || nextPosition[1] >= n) {
                continue;
            }
            int nextPositionCode = encodePosition(nextPosition[0], nextPosition[1], n);
            prob += ((double)1/8) * dfs(n, k-1, nextPositionCode);
        }
        memo[positionCode][k] = prob;
        return prob;
    }

    private static int encodePosition(int row, int column, int n) {
        return row * n + column;
    }

    private static int[] decodePosition(int positionCode, int n) {
        return new int[]{positionCode / n, positionCode % n};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int n = 1;
        // int k = 0;
        // int row = 0;
        // int column = 0;
        int n = 3;
        int k = 2;
        int row = 0;
        int column = 0;
        System.out.println("test: " + sol.knightProbability(n, k, row, column));
    }
}