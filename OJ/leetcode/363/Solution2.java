import java.util.TreeSet;

class Solution2 {
    int m;
    int n;
    int[][] sumMatrix;
    public int maxSumSubmatrix(int[][] matrix, int k) {
        m = matrix.length;
        n = matrix[0].length;
        initializeSumMatrix(matrix);
        int res = Integer.MIN_VALUE;

        // top row
        for (int top = 1; top < m+1; top++) {
            // bottom row
            for (int bottom = top; bottom < m+1; bottom++) {
                // store sorted collection for sumOfRightRegion
                TreeSet<Integer> ts = new TreeSet<>();
                // add 0 is for the specify case of k < frist right
                ts.add(0);
                
                // move the region from left to right
                for (int right = 1; right < n+1; right++) {
                    Integer sumOfRightRegion = sumMatrix[bottom][right] - sumMatrix[top-1][right];
                    // sum[left-1] >= sum[right] - k
                    Integer sumOfLeftRegion = ts.ceiling(sumOfRightRegion - k);
                    if (sumOfLeftRegion != null) {
                        int cur = sumOfRightRegion - sumOfLeftRegion;
                        if (cur == k) return k;
                        res = Math.max(cur, res);
                    }                    
                    ts.add(sumOfRightRegion);
                }
            }
        }
        return res;
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
        Solution2 sol = new Solution2();
        System.out.println("maxSumSubmatrix: "+ sol.maxSumSubmatrix(matrix, k));
    }
}