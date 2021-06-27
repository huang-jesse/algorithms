import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Solution {
    int targetStatus;
    int initStatus = 1;
    int[] targetsOfsnakesOrLadders;
    int n;
    int m;
    public int snakesAndLadders(int[][] board) {
        this.n = board.length;
        this.m = board[0].length;
        this.targetStatus = n*m;
        if (this.targetStatus == initStatus)
            return 0;
        this.targetsOfsnakesOrLadders = getTargetsOfsnakesOrLadders(board);

        Queue<Integer> statussQueue = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();
        statussQueue.add(initStatus);
        seen.add(initStatus);
        int step = 1;
        while (!statussQueue.isEmpty()) {
            int currentSize = statussQueue.size();
            for (int i = 0; i < currentSize; i++) {
                int currentStatus = statussQueue.poll();
                List<Integer> nextStatuss = getNextStatuss(currentStatus);
                for (int nextStatus : nextStatuss) {
                    if (!seen.contains(nextStatus)) {
                        if (nextStatus == targetStatus)
                            return step;
                        statussQueue.add(nextStatus);
                        seen.add(nextStatus);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private int[] getTargetsOfsnakesOrLadders(int[][] board) {
        int[] nums = new int[this.n * this.m + 1];
        int current = 1;
        boolean isRight = true;
        for (int row = n-1; row >= 0; row--) {
            if (isRight) {
                for (int cell = 0; cell < m; cell++) {
                    int target = board[row][cell];
                    nums[current++] = target;
                }
            } else {
                for (int cell = m-1; cell >= 0; cell--) {
                    int target = board[row][cell];
                    nums[current++] = target;
                }
            }
            isRight = !isRight;
        }
        return nums;
    }

    private List<Integer> getNextStatuss(int status) {
        List<Integer> nextStatuss = new ArrayList<>();
        for (int i = 1; i <= 6 && status + i <= n*m; i++) {
            int nextStatus = status + i;
            if (targetsOfsnakesOrLadders[nextStatus] != -1) {
                nextStatus = targetsOfsnakesOrLadders[nextStatus];
            }
            nextStatuss.add(nextStatus);
        }
        return nextStatuss;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] board = {
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,35,-1,-1,13,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,15,-1,-1,-1,-1}
        };
        System.out.println("test: " + sol.snakesAndLadders(board));
    }
}