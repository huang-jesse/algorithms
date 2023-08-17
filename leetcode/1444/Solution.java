import java.util.Arrays;

class Solution {
    private static final int MOD = (int)(1e9 + 7);
    private int[][][] memo;
    private int[][] suffixSumArr;
    private int n;
    private int m;
    public int ways(String[] pizza, int k) {
        this.n = pizza.length;
        this.m = pizza[0].length();
        this.memo = new int[n][m][k];
        this.suffixSumArr = getSuffixSumArr(pizza);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return backtrack(pizza, 0, 0, k - 1);
    }

    private int backtrack(String[] pizza, int row, int col, int k) {
        if (row >= n || col >= m) {
            return 0;
        }
        if (k == 0) {
            if (suffixSumArr[row][col] > 0) {
                // has apple
                return 1;
            } else {
                return 0;
            }
        }
        if (this.memo[row][col][k] != -1) {
            return this.memo[row][col][k];
        }
        int res = 0;
        // vertical
        for (int i = row + 1; i < n; i++) {
            if (hasApple(row, col, i, col)) {
                res = (res + backtrack(pizza, i, col, k - 1)) % MOD;
            }
        }
        // horizontal
        for (int j = col + 1; j < m; j++) {
            if (hasApple(row, col, row, j)) {
                res = (res + backtrack(pizza, row, j, k - 1)) % MOD;
            }
        }
        this.memo[row][col][k] = res;
        return res;
    }

    private boolean hasApple(int startRow, int startCol, int endRow, int endCol) {
        return suffixSumArr[startRow][startCol] - suffixSumArr[endRow][endCol] > 0;
    }

    private int[][] getSuffixSumArr(String[] pizza) {
        int[][] suffixSumArr = new int[n + 1][m + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (pizza[i].charAt(j) == 'A') {
                    suffixSumArr[i][j]++;
                }
                suffixSumArr[i][j] += suffixSumArr[i + 1][j] + suffixSumArr[i][j + 1] - suffixSumArr[i + 1][j + 1];
            }
        }
        return suffixSumArr;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] pizza = {"A..","AAA","..."};
        int k = 3;
        System.out.println("test: " + sol.ways(pizza, k));
    }
}