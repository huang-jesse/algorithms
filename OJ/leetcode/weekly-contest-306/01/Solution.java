class Solution {
    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] maxLocal = new int[n-2][n-2];
        for (int i = 0; i < n-2; i++) {
            for (int j = 0; j < n-2; j++) {
                maxLocal[i][j] = getMaxGrid(grid, i, j);
            }
        }
        return maxLocal;
    }

    private static int getMaxGrid(int[][] grid, int i, int j) {
        int max = 0;
        max = Math.max(max, grid[i][j]);
        max = Math.max(max, grid[i][j+1]);
        max = Math.max(max, grid[i][j+2]);
        max = Math.max(max, grid[i+1][j]);
        max = Math.max(max, grid[i+1][j+1]);
        max = Math.max(max, grid[i+1][j+2]);
        max = Math.max(max, grid[i+2][j]);
        max = Math.max(max, grid[i+2][j+1]);
        max = Math.max(max, grid[i+2][j+2]);
        return max;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{}};
        System.out.println("test: " + sol.largestLocal(grid));
    }
}