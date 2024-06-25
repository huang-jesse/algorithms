import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SolutionOptimization {
    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // {mask, rowIndex}
        Map<Integer, Integer> masksMap = new HashMap<>();
        // bit-masks
        for (int i = 0; i < m; i++) {
            int mask = 0;
            for (int j = 0; j < n; j++) {
                mask |= (grid[i][j] << j);
            }
            masksMap.putIfAbsent(mask, i);
        }
        if (masksMap.containsKey(0)) {
            // all equals 0
            return Arrays.asList(masksMap.get(0));
        }
        for (Map.Entry<Integer, Integer> entry1 : masksMap.entrySet()) {
            int mask1 = entry1.getKey();
            int rowIndex1 = entry1.getValue();
            for (Map.Entry<Integer, Integer> entry2 : masksMap.entrySet()) {
                int mask2 = entry2.getKey();
                int rowIndex2 = entry2.getValue();
                if ((mask1 & mask2) == 0) {
                    return Arrays.asList(Math.min(rowIndex1, rowIndex2), Math.max(rowIndex1, rowIndex2));
                }
            }
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[][] grid = {{0,1,1,0}, {0,0,0,1}, {1,1,1,1}};
        // int[][] grid = {{1,1,1}, {1,1,1}};
        System.out.println("test: " + sol.goodSubsetofBinaryMatrix(grid));
    }
}