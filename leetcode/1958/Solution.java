class Solution {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
    private static final char DOT = '.';
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        int n = board.length;
        for (int[] direction : DIRECTIONS) {
            int r = rMove + direction[0];
            int c = cMove + direction[1];
            if (r < 0 || r >= n || c < 0 || c >= n || board[r][c] != (color ^ 'B' ^ 'W')) {
                continue;
            }

            for (int i = 0; i < n; i++) {
                r = r + direction[0];
                c = c + direction[1];
                if (r < 0 || r >= n || c < 0 || c >= n || board[r][c] == DOT) break;
                if (color == board[r][c]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        char[][] board = {{'.','.','.','B','.','.','.','.'},{'.','.','.','W','.','.','.','.'},{'.','.','.','W','.','.','.','.'},{'.','.','.','W','.','.','.','.'},{'W','B','B','.','W','W','W','B'},{'.','.','.','B','.','.','.','.'},{'.','.','.','B','.','.','.','.'},{'.','.','.','W','.','.','.','.'}};
        int rMove = 4;
        int cMove = 3;
        char color = 'B';
        System.out.println("test: " + sol.checkMove(board, rMove, cMove, color));
    }
}