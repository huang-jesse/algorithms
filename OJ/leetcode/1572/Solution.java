class Solution {
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int sum = 0;
        for (int col = 0, row = 0; col < n; col++, row++) {
            sum += mat[row][col];
        }
        for (int col = n - 1, row = 0; col >= 0; col--, row++) {
            sum += mat[row][col];
        }
        if (n % 2 == 1) {
            // 奇数列，减去对角线交叉区域
            int duplicateCol = mat[n / 2][n / 2];
            sum -= duplicateCol;
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] mat =  {{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println("test: " + sol.diagonalSum(mat));
    }
}