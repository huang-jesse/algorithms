import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            // all col equals 0
            if (Arrays.stream(grid[i]).sum() == 0) {
                return Arrays.asList(i);
            }
        }
        // find googSubset via length of 2
        for (int i = 0; i < m - 1; i++) {
            int[] rowi = grid[i];
            for (int j = i + 1; j < m; j++) {
                int[] rowj = grid[j];
                boolean isGood = true;
                for (int k = 0; k < n; k++) {
                    if (rowi[k] + rowj[k] >= 2) {
                        isGood = false;
                        break;
                    }
                }
                if (isGood) {
                    return Arrays.asList(i, j);
                }
            }
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[][] grid = {{0,1,1,0}, {0,0,0,1}, {1,1,1,1}};
        int[][] grid = {{1,1,1}, {1,1,1}};
        System.out.println("test: " + sol.goodSubsetofBinaryMatrix(grid));
    }
}