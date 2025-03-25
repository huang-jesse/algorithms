import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[][] differenceOfDistinctValues(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 遍历左上对角线(从左上往右下)
        Set<Integer> distinctSet = new HashSet<>();
        int[][] leftAboveDistincts = new int[m][n];
        for (int i = 0; i < m; i++) {
            distinctSet.clear();
            for (int r = i, c = 0; r < m && c < n; r++, c++) {
                leftAboveDistincts[r][c] = distinctSet.size();
                distinctSet.add(grid[r][c]);
            }
        }
        for (int i = 0; i < n; i++) {
            distinctSet.clear();
            for (int r = 0, c = i; r < m && c < n; r++, c++) {
                leftAboveDistincts[r][c] = distinctSet.size();
                distinctSet.add(grid[r][c]);
            }
        }
        // 遍历右下对角线(从右下往左上)
        int[][] rightBelowDistincts = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            distinctSet.clear();
            for (int r = i, c = n - 1; r >= 0 && c >= 0; r--, c--) {
                rightBelowDistincts[r][c] = distinctSet.size();
                distinctSet.add(grid[r][c]);
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            distinctSet.clear();
            for (int r = m - 1, c = i; r >= 0 && c >= 0; r--, c--) {
                rightBelowDistincts[r][c] = distinctSet.size();
                distinctSet.add(grid[r][c]);
            }
        }

        // 计算答案
        int[][] ans = new int[m][n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                ans[r][c] = Math.abs(leftAboveDistincts[r][c] - rightBelowDistincts[r][c]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{1,2,3},{3,1,5},{3,2,1}};
        // ans: [[1,1,0],[1,0,1],[0,1,1]]
        System.out.println("test: ");
        int[][] ans = sol.differenceOfDistinctValues(grid);
        for (int[] row : ans) {
            System.out.println(Arrays.toString(row));
        }
    }
}