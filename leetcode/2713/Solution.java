import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int maxIncreasingCells(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Map<Integer, List<int[]>> valuesMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                valuesMap.computeIfAbsent(mat[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }
        int[] valuesArr = valuesMap.keySet().stream().mapToInt(o -> o).toArray();
        // sort values by asc order
        Arrays.sort(valuesArr);
        int[] rowsMax = new int[m];
        int[] colsMax = new int[n];
        int ans = 0;
        // iterate values in asc order
        for (int value : valuesArr) {
            List<int[]> indexes = valuesMap.get(value);
            int[] maxArr = new int[indexes.size()];
            for (int k = 0; k < indexes.size(); k++) {
                int i = indexes.get(k)[0];
                int j = indexes.get(k)[1];
                maxArr[k] = Math.max(rowsMax[i], colsMax[j]) + 1;
                ans = Math.max(ans, maxArr[k]);
            }
            // update rowsMax and colsMax
            for (int k = 0; k < indexes.size(); k++) {
                int i = indexes.get(k)[0];
                int j = indexes.get(k)[1];
                rowsMax[i] = Math.max(maxArr[k], rowsMax[i]);
                colsMax[j] = Math.max(maxArr[k], colsMax[j]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] mat = {{3,1,6},{-9,5,7}};
        System.out.println("test: " + sol.maxIncreasingCells(mat));
    }
}