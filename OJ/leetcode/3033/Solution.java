import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int[][] modifiedMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] ans = new int[m][n];
        for (int c = 0; c < n; c++) {
            int max = 0;
            for (int r = 0; r < m; r++) {
                max = Math.max(matrix[r][c], max);
            }
            for (int r = 0; r < m; r++) {
                if (matrix[r][c] == -1) {
                    ans[r][c] = max;
                } else {
                    ans[r][c] = matrix[r][c];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] matrix = {{1,2,-1},{4,-1,6},{7,8,9}};
        int[][] ans = sol.modifiedMatrix(matrix);
        List<List<Integer>> ansList = Arrays.stream(ans).map(arr -> Arrays.stream(arr).boxed().collect(Collectors.toList())).collect(Collectors.toList());
        System.out.println("test: " + ansList);
    }
}