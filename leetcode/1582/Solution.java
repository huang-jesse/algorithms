class Solution {
    public int numSpecial(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        boolean[] markRows = new boolean[n];
        boolean[] markCols = new boolean[m];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += mat[i][j];
            }
            if (sum == 1) {
                markRows[i] = true;
            }
        }

        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += mat[j][i];
            }
            if (sum == 1) {
                markCols[i] = true;
            }
        }

        // check
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1 && markRows[i] && markCols[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] mat = {{0,0,0,1}, {1,0,0,0}, {0,1,1,0}, {0,0,0,0}};
        // int[][] mat = {{1}};
        // int[][] mat = {{1,0}};
        // int[][] mat = {{0},{1}};
        // int[][] mat = {{0}};
        System.out.println("test: " + sol.numSpecial(mat));
    }
}