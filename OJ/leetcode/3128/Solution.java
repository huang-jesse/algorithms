class Solution {
    public long numberOfRightTriangles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] rowCounter = new int[m];
        int[] colCounter = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowCounter[i] += grid[i][j];
                colCounter[j] += grid[i][j];
            }
        }
        long ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int rowCount = rowCounter[i] - 1;
                    int colCount = colCounter[j] - 1;
                    ans += rowCount * colCount;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{1,0,1},{1,0,0},{1,0,0}};
        System.out.println("test: " + sol.numberOfRightTriangles(grid));
    }
}