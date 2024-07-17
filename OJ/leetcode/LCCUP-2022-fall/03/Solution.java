import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    // top, right, bottom, left
    private static final int[][] forwards = {{-1,0}, {0,1}, {1,0}, {0,-1}};
    private static final char E = 'E';
    private static final char W = 'W';
    private static final char O = 'O';
    private static final char DOT = '.';
    private int[][][] memo;
    public int[][] ballGame(int num, String[] plate) {
        int n = plate.length;
        int m = plate[0].length();
        // [row][col][forward]
        this.memo = new int[n][m][4];
        char[][] charPlate = new char[n][m];
        for (int i = 0; i < n; i++) {
            charPlate[i] = plate[i].toCharArray();
        }
        Set<Integer> indexesSet = new HashSet<>();
        // top and bottom
        for (int col = 1; col < m - 1; col++) {
            int curRow = 0;
            int curCol = col;
            int steps = 0;
            if (charPlate[curRow][curCol] == DOT) {
                steps = backtrack(charPlate, curRow, curCol, 2);
                if (steps >= 0 && steps <= num) {
                    indexesSet.add(curRow * 1000 + curCol);
                }
            }
            curRow = n - 1;
            curCol = col;
            if (charPlate[curRow][curCol] == DOT) {
                steps = backtrack(charPlate, curRow, curCol, 0);
                if (steps >= 0 && steps <= num) {
                    indexesSet.add(curRow * 1000 + curCol);
                }
            }
        }

        // left and right
        for (int row = 1; row < n - 1; row++) {
            int curRow = row;
            int curCol = 0;
            int steps = 0;
            if (charPlate[curRow][curCol] == DOT) {
                steps = backtrack(charPlate, curRow, curCol, 1);
                if (steps > 0 && steps <= num) {
                    indexesSet.add(curRow * 1000 + curCol);
                }
            }
            curRow = row;
            curCol = m  - 1;
            if (charPlate[curRow][curCol] == DOT) {
                steps = backtrack(charPlate, curRow, curCol, 3);
                if (steps > 0 && steps <= num) {
                    indexesSet.add(curRow * 1000 + curCol);
                }
            }
        }
        int size = indexesSet.size();
        List<Integer> indexesList = new ArrayList<>(indexesSet);
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            ans[i] = new int[]{indexesList.get(i) / 1000, indexesList.get(i) % 1000};
        }
        return ans;
    }

    private int backtrack(char[][] charPlate, int row, int col, int forward) {
        int n = charPlate.length;
        int m = charPlate[0].length;
        if (row < 0 || row >= n || col < 0 || col >= m) {
            return -1;
        }
        if (memo[row][col][forward] != 0) {
            return memo[row][col][forward];
        }
        if (charPlate[row][col] == O) {
            return 0;
        }
        char cur =  charPlate[row][col];
        int nextForward = forward;
        if (cur == E) {
            // clockwise
            nextForward = (forward + 1) % 4;
        } else if (cur == W) {
            // anticlockwise
            nextForward = (forward - 1 + 4) % 4;
        }
        int nextRow = row + forwards[nextForward][0];
        int nextCol = col + forwards[nextForward][1];
        int res = backtrack(charPlate, nextRow, nextCol, nextForward);
        if (res < 0) {
            // unreachable
            memo[row][col][forward] = -1;
        } else {
            memo[row][col][forward] = res + 1;
        }
        return memo[row][col][forward];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int num = 4;
        String[] plate = {"..E.",".EOW","..W."};
        int[][] ans = sol.ballGame(num, plate);
        System.out.println("test: " + Arrays.stream(ans).map(arr -> {
            return Arrays.stream(arr).boxed().collect(Collectors.toList());
        }).collect(Collectors.toList()));
    }
}