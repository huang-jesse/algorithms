import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private int[][] memo;
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
        int emptySize = emptyList.size();
        int stoneSize = stoneList.size();
        this.memo = new int[emptySize][1 << (stoneSize + 1)];
        for (int i = 0; i < emptySize; i++) {
            Arrays.fill(memo[i], -1);
        }
        return backtrack(emptyList, 0, stoneList, 0);
    }

    private int backtrack(List<int[]> emptyList, int index, List<int[]> stoneList, int selectedMask) {
        int n = emptyList.size();
        if (index >= n) {
            return 0;
        }
        if (this.memo[index][selectedMask] != -1) {
            return this.memo[index][selectedMask];
        }
        // 当前空单元格
        int[] emptyCell = emptyList.get(index);
        // 选取 stone
        int m = stoneList.size();
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            int[] stone = stoneList.get(i);
            if (((selectedMask >> i) & 1) != 1) {
                int distance = Math.abs(stone[0] - emptyCell[0]) + Math.abs(stone[1] - emptyCell[1]);
                selectedMask |= (1 << i);
                res = Math.min(res, distance + backtrack(emptyList, index + 1, stoneList, selectedMask));
                selectedMask ^= (1 << i);
            }
        }
        this.memo[index][selectedMask] = res;
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{1,3,0},{1,0,0},{1,0,3}};
        System.out.println("test: " + sol.minimumMoves(grid));
    }
}