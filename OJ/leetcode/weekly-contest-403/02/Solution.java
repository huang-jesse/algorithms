
class Solution {
    public int minimumArea(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int left = m;
        int right = -1;
        int top = n;
        int bottom = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    left = Math.min(left, j);
                    top = Math.min(top, i);
                }
                if (grid[i][m - 1 - j] == 1) {
                    right = Math.max(right, m - 1 - j);
                }
                if (grid[n - 1 - i][j] == 1) {
                    bottom = Math.max(bottom, n - 1 - i);
                }
            }
        }
        int length = right - left + 1;
        int height = bottom - top + 1;
        return length * height;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {};
        System.out.println("test: " + sol.minimumArea(grid));
    }
}