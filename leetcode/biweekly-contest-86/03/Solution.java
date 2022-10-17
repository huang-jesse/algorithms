import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    private boolean[][] memo;
    private int[][] mat;
    private int ans;
    public int maximumRows(int[][] mat, int cols) {
        int m = mat.length;
        int n = mat[0].length;
        if (cols == n) {
            return m;
        }
        this.memo = new boolean[cols][1 << n];
        this.mat = mat;
        ans = 0;
        Set<Integer> colSet = IntStream.range(0, n).boxed().collect(Collectors.toSet());
        backtrack(colSet, 0, cols);
        return ans;
    }

    private void backtrack(Set<Integer> colSet, int mask, int cols) {
        if (cols == 0) {
            ans = Math.max(ans, checkCoveredRows(mask));
            return;
        }
        if (memo[cols - 1][mask]) {
            return;
        }
        List<Integer> colList = colSet.stream().collect(Collectors.toList());
        for (int col : colList) {
            colSet.remove(col);
            mask = mask | (1 << col);
            backtrack(colSet, mask, cols - 1);
            colSet.add(col);
            mask = mask ^ (1 << col);
        }
        memo[cols - 1][mask] = true;
    }

    private int checkCoveredRows(int mask) {
        int m = mat.length;
        int n = mat[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            boolean isRowOk = true;
            for (int j = 0; j < n; j++) {
                boolean isSelected = (mask & (1 << j)) == 1 << j;
                if (!isSelected && mat[i][j] == 1) {
                    isRowOk = false;
                    break;
                }
            }
            if (isRowOk) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] mat = {{0,0,0},{1,0,1},{0,1,1},{0,0,1}};
        int cols = 2;
        System.out.println("test: " + sol.maximumRows(mat, cols));
    }
}