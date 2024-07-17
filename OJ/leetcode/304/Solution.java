/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
class NumMatrix {
    int m;
    int n;
    int[][] sumMatrix;
    public NumMatrix(int[][] matrix) {
        m = matrix.length;
        if (m == 0) return;
        n = matrix[0].length;
        initializeSumMatrix(matrix);
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        // sumRegion(row1,col1,row2,col2) = f(row2,col2) - f(row1-1,col2) - f(row2,col1-1) + f(row1-1,col1-1)
        int sumOfRegion = sumMatrix[row2+1][col2+1] - sumMatrix[row1][col2+1] - sumMatrix[row2+1][col1] + sumMatrix[row1][col1];
        return sumOfRegion;
    }

    private void initializeSumMatrix(int[][] matrix) {
        // m+1 and n+1 is for aviod boundary case of (0,0)
        sumMatrix = new int[m+1][n+1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // f(i,j) = f(i,j-1) + f(i-1,j) - f(i-1,j-1) + matrix[i][j]
                sumMatrix[i+1][j+1] = sumMatrix[i+1][j] + sumMatrix[i][j+1] - sumMatrix[i][j] + matrix[i][j];
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]
        {
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}
        };
        
        NumMatrix sol = new NumMatrix(matrix);
        System.out.println("sumRegion: "+ sol.sumRegion(1, 2, 2, 4));
    }
}