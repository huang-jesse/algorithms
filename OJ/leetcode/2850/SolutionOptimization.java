import java.util.ArrayList;
import java.util.List;

class SolutionOptimization {
    private static final int INF = 0x3fffffff;
    public int minimumMoves(int[][] grid) {
        int n = grid.length;
        List<int[]> emptyCells = new ArrayList<>();
        List<int[]> stoneCells = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0) {
                    emptyCells.add(new int[]{r, c});
                } else if (grid[r][c] > 1) {
                    stoneCells.add(new int[]{r, c});
                }
            }
        }
        return backtrack(grid, emptyCells, stoneCells, 0);
    }

    private int backtrack(int[][] grid, List<int[]> emptyCells, List<int[]> stoneCells, int index) {
        if (index == emptyCells.size()) return 0;
        int[] currentCell = emptyCells.get(index);
        int res = INF;
        for (int[] destCell : stoneCells) {
            int destR = destCell[0];
            int destC = destCell[1];
            if (grid[destR][destC] == 1) continue;
            grid[destR][destC]--;
            res = Math.min(res, backtrack(grid, emptyCells, stoneCells, index + 1) + move(currentCell, destCell));
            grid[destR][destC]++;
        }
        return res;
    }

    private int move(int[] from, int[] to) {
        return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[][] grid = {{1,3,0},{1,0,0},{1,0,3}};
        System.out.println("test: " + sol.minimumMoves(grid));
    }
}