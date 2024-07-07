
class Solution {
    private static final char X = 'X';
    private static final char Y = 'Y';
    private static final char DOT = '.';
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] xCounter = counter(grid, X);
        int[][] yCounter = counter(grid, Y);
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (xCounter[i + 1][j + 1] < 1) continue;
                if (xCounter[i + 1][j + 1] != yCounter[i + 1][j + 1]) continue;
                ans++;
            }
        }
        return ans;
    }

    private int[][] counter(char[][] grid, char target) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] counter = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 0;
                if (grid[i][j] == target) {
                    cnt = 1;
                }
                counter[i + 1][j + 1] = counter[i + 1][j] + cnt;
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                counter[i + 1][j + 1] += counter[i][j + 1];
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        char[][] grid = {{'X','Y','.'}, {'Y','.','.'}};
        System.out.println("test: " + sol.numberOfSubmatrices(grid));
    }
}