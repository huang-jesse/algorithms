class SolutionUp {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] up = new int[m][n];
        // col
        for (int col = 0; col < n; col++) {
            // row
            for (int row = 0; row < m; row++) {
                if (matrix[row][col] == '1') {
                    up[row][col] = 1;
                    if (row > 0) {
                        up[row][col]= up[row][col] + up[row - 1][col];
                    }
                }
            }
        }

        int ans = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int height = Integer.MAX_VALUE;
                for (int k = col; k >= 0; k--) {
                    int cur = up[row][k];
                    if (cur == 0) {
                        break;
                    }
                    height = Math.min(height, cur);
                    int width = col - k + 1;
                    int area = width * height;
                    ans = Math.max(ans, area);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionUp sol = new SolutionUp();
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        // char[][] matrix = {{'0','0'}};
        // char[][] matrix = {{'1','1'}};
        // char[][] matrix = {{'1'}};
        // char[][] matrix = {{'0'}};
        // char[][] matrix = {{}};
        System.out.println("test: " + sol.maximalRectangle(matrix));
    }
}