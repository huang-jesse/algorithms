import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] newMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            newMatrix[i] = matrix[i].clone();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // preCol
                int nextRow = j;
                // (n - 1) - preRow
                int nextCol = (n - 1) - i;
                matrix[nextRow][nextCol] = newMatrix[i][j];
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        sol.rotate(matrix);
        List<List<Integer>> allList = Arrays.stream(matrix).map(row -> {
            return Arrays.stream(row).boxed().collect(Collectors.toList());
        }).collect(Collectors.toList());
        System.out.println("test: " + allList);
    }
}