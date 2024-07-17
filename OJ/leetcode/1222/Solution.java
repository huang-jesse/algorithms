import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private final int[][] directions = {
        {0, -1}, {0, 1}, {-1, 0}, {1, 0}
        ,{-1, -1}, {1, -1}, {-1, 1}, {1, 1}
    };
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        boolean[][] isQueen = new boolean[8][8];
        for (int[] queen : queens) {
            isQueen[queen[0]][queen[1]] = true;
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] direction : directions) {
            for (int i = 0; i < 8; i++) {
                int[] next = new int[]{king[0] + direction[0] * i, king[1] + direction[1] * i};
                if (next[0] >= 0 && next[0] < 8 && next[1] >= 0 && next[1] < 8) {
                    if (isQueen[next[0]][next[1]]) {
                        ans.add(Arrays.asList(next[0], next[1]));
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] queens = {{0,0},{1,1},{2,2},{3,4},{3,5},{4,4},{4,5}};
        int[] king = {3,3};
        System.out.println("test: " + sol.queensAttacktheKing(queens, king));
    }
}