import java.util.Arrays;
import java.util.List;

class Solution {
    private static final int NEGATIVE_INF = -0x3fffffff;
    public int maxScore(List<List<Integer>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();
        // bottom-right max
        int[][] maxGrid = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) maxGrid[i][n] = NEGATIVE_INF;
        for (int j = 0; j < n; j++) maxGrid[m][j] = NEGATIVE_INF;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                maxGrid[i][j] = Math.max(grid.get(i).get(j), Math.max(maxGrid[i + 1][j], maxGrid[i][j + 1]));
            }
        }
        int ans = NEGATIVE_INF;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = grid.get(i).get(j);
                int res = Math.max(maxGrid[i + 1][j] - num, maxGrid[i][j + 1] - num);
                ans = Math.max(ans, res);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<List<Integer>> grid = Arrays.asList(Arrays.asList(4,3,2), Arrays.asList(3,2,1));
        System.out.println("test: " + sol.maxScore(grid));
    }
}