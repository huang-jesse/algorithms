import java.util.HashMap;
import java.util.Map;

class SolutionOptimization {
    private int[][] grid;
    private final int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    private Map<Long, Integer> memo;
    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        memo = new HashMap<>();
        int n = grid.length;
        int m = grid[0].length;
        int startRow = 0;
        int startCol = 0;
        int mask = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    startRow = i;
                    startCol = j;
                }
                if (grid[i][j] == 0 || grid[i][j] == 2) {
                    // 标记空单元格和终点为可访问单元格
                    mask |= (1 << (i * m + j));
                }
            }
        }
        return backtrack(startRow, startCol, mask);
    }

    private int backtrack(int i, int j, int mask) {
        int n = grid.length;
        int m = grid[0].length;
        // 到达终点
        if (grid[i][j] == 2) {
            // 访问了全部单元格
            if (mask == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        int res = 0;
        long ijMask = (i * m + j) << (n * m);
        long key = ijMask + mask;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        for (int[] direction : directions) {
            int row = i + direction[0];
            int col = j + direction[1];
            if (row < 0 || row >= n || col < 0 || col >= m) {
                continue;
            }
            int currentMask = 1 << (row * m + col);
            // 该单元格还未访问
            if ((currentMask & mask) > 0) {
                mask ^= currentMask;
                res += backtrack(row, col, mask);
                mask ^= currentMask;
            }
        }
        memo.put(key, res);
        return res;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        System.out.println("test: " + sol.uniquePathsIII(grid));
    }
}