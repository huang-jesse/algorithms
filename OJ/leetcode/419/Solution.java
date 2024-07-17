class Solution {
    private static final char BATTLESHIP_CHAR = 'X';
    private static final char EMPTY_CHAR = '.';
    public int countBattleships(char[][] board) {
        int rowLen = board.length;
        int cellLen = board[0].length;
        int ans = 0;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < cellLen; j++) {
                if (board[i][j] == BATTLESHIP_CHAR
                    && (i == 0 || board[i-1][j] == EMPTY_CHAR)
                    && (j == 0 || board[i][j-1] == EMPTY_CHAR)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        char[][] board = {{'X','.','.','X'},{'.','.','.','X'},{'.','.','.','X'}};
        System.out.println("test: " + sol.countBattleships(board));
    }
}