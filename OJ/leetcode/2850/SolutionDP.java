import java.util.ArrayList;
import java.util.List;

class SolutionDP {
    public int minimumMoves(int[][] grid) {
        int n = grid.length;
        List<int[]> stoneList = new ArrayList<>();
        List<int[]> emptyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    emptyList.add(new int[]{i, j});
                } else if (grid[i][j] > 1) {
                    for (int k = 0; k < grid[i][j] - 1; k++) {
                        // 将每个石头分别放入 list （只留下一个在当前单元格）
                        stoneList.add(new int[]{i, j});
                    }
                }
            }
        }
        int m = emptyList.size();
        // dp[mask] 表示 stoneList 中被 mask 代表的下标已经被匹配的最小代价
        int[] dp = new int[1 << m];
        dp[0] = 0;
        for (int i = 1; i < (1 << m); i++) {
            int cnt = 0;
            // 计算 i 有几个二进制位等于 1 (即 i 已经匹配了多少项)
            for (int j = 0; j < m; j++) {
                cnt += ((i >> j) & 1);
            }
            // 当前需要选取的空单元格
            int[] emtpyCell = emptyList.get(cnt - 1);
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                if (((i >> j) & 1) == 1) {
                    // 选取了第 j 个 stoneList
                    dp[i] = Math.min(dp[i], dp[i ^ (1 << j)] + cost(emtpyCell, stoneList.get(j)));
                }
            }
        }
        return dp[(1 << m) - 1];
    }

    private int cost(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    public static void main(String[] args) {
        SolutionDP sol = new SolutionDP();
        int[][] grid = {{1,3,0},{1,0,0},{1,0,3}};
        System.out.println("test: " + sol.minimumMoves(grid));
    }
}