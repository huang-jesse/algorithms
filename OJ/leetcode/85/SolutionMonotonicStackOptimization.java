import java.util.ArrayDeque;
import java.util.Deque;

class SolutionMonotonicStackOptimization {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] left = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = 1;
                    if (j > 0) {
                        left[i][j] = left[i][j] + left[i][j - 1];
                    }
                }
            }
        }

        int ans = 0;
        for (int col = 0; col < n; col++) {
            int[] up = new int[m];
            int[] down = new int[m];
            Deque<Integer> stack = new ArrayDeque<>();
            for (int row = 0; row < m; row++) {
                int curWidth = left[row][col];
                while (!stack.isEmpty() && left[stack.peek()][col] >= curWidth) {
                    down[stack.peek()] = row;
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    up[row] = -1;
                } else {
                    up[row] = stack.peek();
                }
                stack.push(row);
            }
            while (!stack.isEmpty()) {
                down[stack.pop()] = m;
            }

            //calculate
            for (int row = 0; row < m; row++) {
                int height = down[row] - up[row] - 1;
                int width = left[row][col];
                int area = width * height;
                ans = Math.max(ans, area);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionMonotonicStackOptimization sol = new SolutionMonotonicStackOptimization();
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        // char[][] matrix = {{'0','0'}};
        // char[][] matrix = {{'1','1'}};
        // char[][] matrix = {{'1'}};
        // char[][] matrix = {{'0'}};
        // char[][] matrix = {{}};
        System.out.println("test: " + sol.maximalRectangle(matrix));
    }
}