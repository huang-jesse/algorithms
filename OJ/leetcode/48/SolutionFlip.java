import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class SolutionFlip {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // horizontal flip
        for (int i = 0; i < (n / 2); i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = temp;
            }
        }
        // diagonal flip
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        SolutionFlip sol = new SolutionFlip();
        // int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        sol.rotate(matrix);
        List<List<Integer>> allList = Arrays.stream(matrix).map(row -> {
            return Arrays.stream(row).boxed().collect(Collectors.toList());
        }).collect(Collectors.toList());
        System.out.println("test: " + allList);
    }
}