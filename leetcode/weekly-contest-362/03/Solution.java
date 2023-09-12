import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int minimumMoves(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }


        return 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {};
        System.out.println("test: " + sol.minimumMoves(grid));
    }
}