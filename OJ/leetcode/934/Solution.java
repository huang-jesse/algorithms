import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        // find one island
        Queue<int[]> queue = new LinkedList<>();
        outer:for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) {
                    queue.offer(new int[]{row, col});
                    break outer;
                }
            }
        }
        // add one island to queue
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> temp = new LinkedList<>();
        temp.offer(queue.peek());
        visited[queue.peek()[0]][queue.peek()[1]] = true;
        while (!temp.isEmpty()) {
            int[] cur = temp.poll();
            for (int[] direction : directions) {
                int[] next = {cur[0] + direction[0], cur[1] + direction[1]};
                if (next[0] < 0 || next[0] >= n
                    || next[1] < 0 || next[1] >= n
                    || grid[next[0]][next[1]] == 0
                    || visited[next[0]][next[1]]) {
                    continue;
                }
                visited[next[0]][next[1]] = true;
                queue.offer(next);
                temp.offer(next);
            }
        }

        // connect to another island
        int ans = 0;
        outer:while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] direction : directions) {
                    int[] next = {cur[0] + direction[0], cur[1] + direction[1]};
                    if (next[0] < 0 || next[0] >= n
                        || next[1] < 0 || next[1] >= n
                        || visited[next[0]][next[1]]) {
                        continue;
                    }
                    if (grid[next[0]][next[1]] == 1) {
                        // connected
                        break outer;
                    }
                    visited[next[0]][next[1]] = true;
                    queue.offer(next);
                }
            }
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{0,1,1,1},{0,0,0,0},{0,0,0,0},{1,1,0,0}};
        // int[][] grid = {{0,1,0},{0,0,0},{0,0,1}};
        System.out.println("test: " + sol.shortestBridge(grid));
    }
}