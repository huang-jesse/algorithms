import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    private static final int MOD = 12345;
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        long product = 1;
        int[][] prefixProductMatrix = new int[n][n];
        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < m; j++) {
                prefixProductMatrix[i][j] = (int)product;
                product = product * grid[i][j] % MOD;
            }
        }
        int[][] suffixProductuctMatrix = new int[n][n];
        product = 1;
        for (int i = n - 1 ; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                suffixProductuctMatrix[i][j] = (int)product;
                product = product * grid[i][j] % MOD;
            }
        }

        int[][] ans = new int[n][m];
        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int prefixProduct = prefixProductMatrix[i][j];
                int suffixProduct = suffixProductuctMatrix[i][j];
                ans[i][j] = (int)((long)prefixProduct * suffixProduct % MOD);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{1,2}, {3,4}};
        int[][] res = sol.constructProductMatrix(grid);
        List<String> ans = Arrays.stream(res).map(o -> Arrays.toString(o)).collect(Collectors.toList());
        System.out.println("test: " + ans);
    }
}