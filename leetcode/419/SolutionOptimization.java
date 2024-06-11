class SolutionOptimization {
    private static final char LETTER_X = 'X';
    public int countBattleships(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != LETTER_X) continue;
                if (board[i][j] == LETTER_X
                && (i == 0 || board[i - 1][j] != LETTER_X)
                && (j == 0 || board[i][j - 1] != LETTER_X)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        char[][] board = {{'X','.','.','X'},{'.','.','.','X'},{'.','.','.','X'}};
        System.out.println("test: " + sol.countBattleships(board));
    }
}