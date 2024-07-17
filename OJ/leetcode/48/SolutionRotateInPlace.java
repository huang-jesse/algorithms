import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class SolutionRotateInPlace {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // from outer circle to inner circle
        for (int i = 0; i < ((n + 1) / 2); i++) {
            // iteration in current circle
            for (int j = i; j < (n - i - 1); j++) {
                // first rotated cell
                int firstRow = i;
                int firstCol = j;
                int temp = matrix[firstRow][firstCol];

                // second rotated cell
                int secondRow = firstCol;
                int secondCol = (n - 1) - firstRow;

                // third rotated cell
                int thirdRow = secondCol;
                int thirdCol = (n - 1) - secondRow;

                // fourth rotated cell
                int fourthRow = thirdCol;
                int fourthCol = (n - 1) - thirdRow;

                // rotate
                matrix[firstRow][firstCol] = matrix[fourthRow][fourthCol];
                matrix[fourthRow][fourthCol] = matrix[thirdRow][thirdCol];
                matrix[thirdRow][thirdCol] = matrix[secondRow][secondCol];
                matrix[secondRow][secondCol] = temp;
            }
        }
    }

    public static void main(String[] args) {
        SolutionRotateInPlace sol = new SolutionRotateInPlace();
        // int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        sol.rotate(matrix);
        List<List<Integer>> allList = Arrays.stream(matrix).map(row -> {
            return Arrays.stream(row).boxed().collect(Collectors.toList());
        }).collect(Collectors.toList());
        System.out.println("test: " + allList);
    }
}