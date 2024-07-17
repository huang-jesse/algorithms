class Solution {
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
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int width = Integer.MAX_VALUE;
                for (int k = i; k >= 0; k--) {
                    int cur = left[k][j];
                    if (cur == 0) {
                        break;
                    }
                    width = Math.min(width, cur);
                    int height = i - k + 1;
                    int area = width * height;
                    ans = Math.max(ans, area);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        // char[][] matrix = {{'0','0'}};
        // char[][] matrix = {{'1','1'}};
        // char[][] matrix = {{'1'}};
        // char[][] matrix = {{'0'}};
        // char[][] matrix = {{}};
        System.out.println("test: " + sol.maximalRectangle(matrix));
    }
}