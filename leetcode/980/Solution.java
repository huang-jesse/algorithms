class Solution {
    private int[][] grid;
    private final int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    private int availableColNum;
    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int startRow = 0;
        int startCol = 0;
        availableColNum = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    startRow = i;
                    startCol = j;
                }
                if (grid[i][j] == 0) {
                    availableColNum++;
                }
            }
        }
        visited[startRow][startCol] = true;
        return backtrack(visited, startRow, startCol, 1);
    }

    private int backtrack(boolean[][] visited, int i, int j, int counter) {
        int n = grid.length;
        int m = grid[0].length;
        if (grid[i][j] == 2) {
            // 到达终点
            if (counter == availableColNum) {
                // 访问了全部单元格
                return 1;
            } else {
                return 0;
            }
        }
        int res = 0;
        for (int[] direction : directions) {
            int row = i + direction[0];
            int col = j + direction[1];
            if (row < 0 || row >= n || col < 0 || col >= m) {
                continue;
            }
            if (!visited[row][col] && grid[row][col] != -1) {
                visited[row][col] = true;
                res += backtrack(visited, row, col, counter + 1);
                visited[row][col] = false;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        System.out.println("test: " + sol.uniquePathsIII(grid));
    }
}