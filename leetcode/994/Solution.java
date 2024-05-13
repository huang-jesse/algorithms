import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int freshCount = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    freshCount++;
                } else if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int elapse = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                for (int[] direction : DIRECTIONS) {
                    int nextI = current[0] + direction[0];
                    int nextJ = current[1] + direction[1];
                    if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || grid[nextI][nextJ] != 1) continue;
                    // rotting
                    freshCount--;
                    grid[nextI][nextJ] = 2;
                    queue.offer(new int[]{nextI, nextJ});
                }
            }
            elapse++;
        }
        return freshCount == 0 ? Math.max(elapse, 0) : -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println("test: " + sol.orangesRotting(grid));
    }
}