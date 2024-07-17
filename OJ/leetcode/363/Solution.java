class Solution {
    int m;
    int n;
    int[][] sumMatrix;
    public int maxSumSubmatrix(int[][] matrix, int k) {
        m = matrix.length;
        n = matrix[0].length;
        initializeSumMatrix(matrix);
        int res = Integer.MIN_VALUE;

        // left-top point
        for (int row1 = 0; row1 < m; row1++) {
            for (int col1 = 0; col1 < n; col1++) {
                // right-bottom point
                for (int row2 = row1; row2 < m; row2++) {
                    for (int col2 = col1; col2 < n; col2++) {
                        int sumOfRegion = sumRegion(row1, col1, row2, col2, k);
                        if (sumOfRegion == k) return k;
                        res = sumOfRegion > k ? res : Math.max(res, sumOfRegion);
                    }
                }
            }
        }

        return res;
    }
        
    private int sumRegion(int row1, int col1, int row2, int col2, int k) {
        // sumRegion(row1,col1,row2,col2) = f(row2,col2) - f(row1-1,col2) - f(row2,col1-1) + f(row1-1,col1-1)
        long sumOfRegion = sumMatrix[row2+1][col2+1] - sumMatrix[row1][col2+1] - sumMatrix[row2+1][col1] + sumMatrix[row1][col1];
        return sumOfRegion > k ? k+1 : (int)sumOfRegion;
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
        int[][] matrix = new int[][]{{1,0,1},{0,-2,3}};
        // int[][] matrix = new int[][]{{2,2,-1}};
        int k = 2;
        Solution sol = new Solution();
        System.out.println("maxSumSubmatrix: "+ sol.maxSumSubmatrix(matrix, k));
    }
}