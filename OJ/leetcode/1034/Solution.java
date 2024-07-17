import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

class Solution {
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        if (grid[row][col] == color)
            return grid;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> coloredList = new ArrayList<>();
        queue.offer(new int[]{row, col});
        int curColor = grid[row][col];
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int curRow = cur[0];
                int curCol = cur[1];
                if (isBorder(grid, curRow, curCol, curColor)) {
                    coloredList.add(new int[]{curRow, curCol});
                }
                List<int[]> nexts = getNexts(grid, curRow, curCol, curColor, visited);
                if (!nexts.isEmpty()) {
                    queue.addAll(nexts);
                }
            }
        }

        for (int[] colored : coloredList) {
            grid[colored[0]][colored[1]] = color;
        }

        return grid;
    }

    private static List<int[]> getNexts(int[][] grid, int row, int col, int color, boolean[][] visited) {
        int m = grid.length;
        int n = grid[0].length;
        List<int[]> nexts = new ArrayList<>();
        int curRow = row;
        int curCol = col;
        // top
        if (row != 0) {
            curRow = row - 1;
            curCol = col;
            addToNexts(grid, curRow, curCol, color, visited, nexts);
        }
        // bottom
        if (row != m-1) {
            curRow = row + 1;
            curCol = col;
            addToNexts(grid, curRow, curCol, color, visited, nexts);
        }
        // left
        if (col != 0) {
            curRow = row;
            curCol = col - 1;
            addToNexts(grid, curRow, curCol, color, visited, nexts);
        }
        // right
        if (col != n-1) {
            curRow = row;
            curCol = col + 1;
            addToNexts(grid, curRow, curCol, color, visited, nexts);
        }
        return nexts;
    }

    private static void addToNexts(int[][] grid, int row, int col, int color, boolean[][] visited, List<int[]> nexts) {
        if (!visited[row][col] && grid[row][col] == color) {
            nexts.add(new int[]{row, col});
            visited[row][col] = true;
        }
    }

    private static boolean isBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length;
        int n = grid[0].length;
        // boundary
        if (row == 0 || row == m-1 || col == 0 || col == n-1) {
            return true;
        }
        // border of a connected component
        if (grid[row-1][col] != color || grid[row+1][col] != color
            || grid[row][col-1] != color || grid[row][col+1] != color) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{1,1},{1,2}};
        int row = 0;
        int col = 0;
        int color = 3;
        // int[][] grid = {{1,2,2},{2,3,2}};
        // int row = 0;
        // int col = 1;
        // int color = 3;
        int[][] result = sol.colorBorder(grid, row, col, color);
        System.out.println("result is: ");
        for (int[] arr : result) {
            System.out.println(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        }
    }
}