import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Solution {
    static final String TARGET_STATUS = "123450";
    int d = 6;
    int[][] neighbors = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
    public int slidingPuzzle(int[][] board) {
        StringBuffer sb = new StringBuffer();
        for (int[] arr : board) {
            for (int digit : arr) {
                sb.append(digit);
            }
        }
        String initStatus = sb.toString();
        if (initStatus.equals(TARGET_STATUS))
            return 0;

        Queue<String> statussQueue = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        statussQueue.offer(initStatus);
        seen.add(initStatus);

        int step = 0;
        while (!statussQueue.isEmpty()) {
            step++;
            int currentSize = statussQueue.size();
            for (int i = 0; i < currentSize; i++) {
                String currentStatus = statussQueue.poll();
                List<String> nextStatuss = getNextStatuss(currentStatus);
                for (String nextStatus : nextStatuss) {
                    if (!seen.contains(nextStatus)) {
                        if (nextStatus.equals(TARGET_STATUS)) {
                            return step;
                        }
                        statussQueue.offer(nextStatus);
                        seen.add(nextStatus);
                    }
                }
            }
        }
        return -1;
    }

    private List<String> getNextStatuss(String status) {
        List<String> nextStatus = new ArrayList<>();
        char[] array = status.toCharArray();
        int x = status.indexOf('0');
        for (int y : neighbors[x]) {
            swap(array, x, y);
            nextStatus.add(new String(array));
            swap(array, x, y);
        }
        return nextStatus;
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] board = {{1,2,3}, {4,0,5}};
        // int[][] board = {{1,2,3}, {5,4,0}};
        // int[][] board = {{3,2,4}, {1,5,0}};
        System.out.println("test: " + sol.slidingPuzzle(board));
    }
}