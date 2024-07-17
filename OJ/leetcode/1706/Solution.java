import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    private static final int LEFT_BOARD = -1;
    private static final int RIGHT_BOARD = 1;
    public int[] findBall(int[][] grid) {
        int n = grid[0].length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = check(grid, 0, i);
        }
        return ans;
    }

    private int check(int[][] grid, int row, int col) {
        int m = grid.length;
        int n = grid[0].length;
        if (row >= m) {
            return col;
        }
        int board = grid[row][col];
        if (board == LEFT_BOARD) {
            // left board
            if (col == 0 || grid[row][col-1] == RIGHT_BOARD) {
                // blocked
                return -1;
            } else {
                row++;
                col--;
            }
        } else {
            // right board
            if (col == n-1 || grid[row][col+1] == LEFT_BOARD) {
                // blocked
                return -1;
            } else {
                row++;
                col++;
            }
        }
        return check(grid, row, col);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{1,1,1,-1,-1},{1,1,1,-1,-1},{-1,-1,-1,1,1},{1,1,1,1,-1},{-1,-1,-1,-1,-1}};
        List<Integer> ans = Arrays.stream(sol.findBall(grid)).boxed().collect(Collectors.toList());
        System.out.println("test: " + ans);
    }
}