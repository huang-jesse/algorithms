class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        char[][] ans = new char[n][m];

        // rotate box
        int row = 0;
        int col = 0;
        for (int i = 0; i < n; i++) {
            for (int j = m-1; j >= 0; j--) {
                ans[row][col] = box[j][i];
                col++;
            }
            col = 0;
            row++;
        }
        
        if (n - 1 <= 0) return ans;
        // move stone
        // bottom to top
        for (int i = n-2; i >= 0; i--) {
            // left to right
            for (int j = 0; j < m; j++) {
                if (ans[i][j] == '#') {
                    // bottom is empty
                    int temp = i;
                    while (temp+1 < n && ans[temp+1][j] == '.') {
                        ans[temp+1][j] = '#';
                        ans[temp][j] = '.';
                        temp++;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        char[][] box = new char[][]{{'#','.','#'}};
        System.out.println("test: " + sol.rotateTheBox(box));
    }
}