import java.util.HashMap;
import java.util.Map;

class Solution {
    public int movesToChessboard(int[][] board) {
        int n = board.length;
        // check
        Map<Integer, Integer> rowMaskCounter = new HashMap<>();
        Map<Integer, Integer> colMaskCounter = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int rowMask = getRowMask(board, i);
            rowMaskCounter.put(rowMask, rowMaskCounter.getOrDefault(rowMask, 0) + 1);
            int colMask = getColMask(board, i);
            colMaskCounter.put(colMask, colMaskCounter.getOrDefault(colMask, 0) + 1);
        }
        if (!check(rowMaskCounter, n) || !check(colMaskCounter, n)) {
            return -1;
        }

        // find answer
        int rowMask1 = getRowMask(board, 0);
        int colMask1 = getColMask(board, 0);
        int ans = calculateMoveSteps(rowMask1, n) + calculateMoveSteps(colMask1, n);
        return ans;
    }

    private boolean check(Map<Integer, Integer> maskCounter, int n) {
        // check
        if (maskCounter.size() != 2) {
            return false;
        }
        Integer[] masks = maskCounter.keySet().toArray(Integer[]::new);
        int mask1 = masks[0];
        int mask2 = masks[1];
        int mask1Count = maskCounter.get(mask1);
        int mask2Count = maskCounter.get(mask2);
        if (Math.abs(mask1Count - mask2Count) > 1) {
            return false;
        }
        int bitOneCount = Integer.bitCount(mask1);
        int bitZeroCount = n - bitOneCount;
        if (Math.abs(bitOneCount - bitZeroCount) > 1) {
            return false;
        }
        // 1111...111(n ones)
        int matchMask = (1 << n) - 1;
        int maskXOR = mask1 ^ mask2;
        if (maskXOR != matchMask) {
            return false;
        }
        return true;
    }

    private int calculateMoveSteps(int mask, int n) {
        int bitOneCount = Integer.bitCount(mask);
        int moveSteps = 0;
        if (n % 2 == 1) {
            // odd
            if (bitOneCount > (n >> 1)) {
                moveSteps = getMoveSteps(true, n, mask);
            } else {
                moveSteps = getMoveSteps(false, n, mask);
            }
        } else {
            // even
            moveSteps = getMoveSteps(true, n, mask);
            moveSteps = Math.min(moveSteps, getMoveSteps(false, n, mask));
        }
        return moveSteps;
    }

    private int getMoveSteps(boolean isOneStart, int n, int mask) {
        int matchMask = generateMatchMask(isOneStart, n);
        int match = mask ^ matchMask;
        int moveSteps = Integer.bitCount(match) / 2;
        return moveSteps;
    }

    /**
     * Generate mask like '010101' or '101010'
     * @param isOneStart
     * @param len
     * @return
     */
    private int generateMatchMask(boolean isOneStart, int len) {
        int mask = 0;
        int aux = 0;
        if (isOneStart) {
            mask = 1 << (len - 1);
            len--;
        }
        while (len > 0) {
            mask = mask | (aux << (len - 1));
            aux = aux ^ 1;
            len--;
        }
        return mask;
    }

    private int getRowMask(int[][] board, int rowIndex) {
        int mask = 0;
        int n = board.length;
        int[] row = board[rowIndex];
        for (int i = 0; i < n; i++) {
            int cur = row[i];
            mask = mask | (cur << ((n - 1) - i));
        }
        return mask;
    }

    private int getColMask(int[][] board, int colIndex) {
        int mask = 0;
        int n = board.length;
        for (int i = 0; i < n; i++) {
            int cur = board[i][colIndex];
            mask = mask | (cur << ((n - 1)) - i);
        }
        return mask;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[][] board = {{0,1},{1,0}};
        int[][] board = {{0,1,1,0},{0,1,1,0},{1,0,0,1},{1,0,0,1}};
        System.out.println("test: " + sol.movesToChessboard(board));
    }
}