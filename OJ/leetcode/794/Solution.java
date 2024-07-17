class Solution {
    public boolean validTicTacToe(String[] board) {
        char[][] cs = new char[3][3];
        int x = 0, o = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = board[i].charAt(j);
                if (c == 'X') x++;
                else if (c == 'O') o++;
                cs[i][j] = c;
            }
        }
        boolean a = check(cs, 'X'), b = check(cs, 'O');
        if (o > x || x - o > 1) return false;
        if (a && x <= o) return false;
        if (b && o != x) return false;
        if (a && b) return false;
        return true;
    }
    boolean check(char[][] cs, char c) {
        for (int i = 0; i < 3; i++) {
            if (cs[i][0] == c && cs[i][1] == c && cs[i][2] == c) return true;
            if (cs[0][i] == c && cs[1][i] == c && cs[2][i] == c) return true;
        }
        if (cs[0][0] == c && cs[0][0] == cs[1][1] && cs[1][1] == cs[2][2]) {
            return true;
        }
        if (cs[0][2] == c && cs[0][2] == cs[1][1] && cs[1][1] == cs[2][0]) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] board = {"XXX","   ","OOO"};
        // String[] board = {"XOX","OXO","XOX"};
        // String[] board = {"   ","   ","   "};
        System.out.println("test: " + sol.validTicTacToe(board));
    }
}