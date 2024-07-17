import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Queue<Integer>[] wallQueues = (LinkedList<Integer>[]) new LinkedList[wall.size()];
        for (int i = 0; i < wall.size(); i++) {
            List<Integer> row = wall.get(i);
            Queue<Integer> rowQueue = new LinkedList<>();
            row.stream().forEach(col -> rowQueue.offer(col));
            wallQueues[i] = rowQueue;
        }

        // answer
        int ans = wallQueues.length;

        // count the number of finished rows
        int noOfFinishedRows = 0;

        // current col of wall
        long[] currentCol = new long[wallQueues.length];

        while (noOfFinishedRows < wallQueues.length) {
            // min of col
            long min = Integer.MAX_VALUE;
            for (int i = 0; i < wallQueues.length; i++) {
                if (currentCol[i] == 0 && !wallQueues[i].isEmpty()) {
                    currentCol[i] = currentCol[i] + wallQueues[i].poll();
                    // count
                    if (wallQueues[i].isEmpty())
                        noOfFinishedRows++;
                }
                min = Math.min(min, currentCol[i]);
            }
            // if in the end of wall, break
            if (noOfFinishedRows == wallQueues.length)
                break;
            // check this col
            ans = Math.min(ans, getLeastBricks(currentCol, min));
            initializeCurrentCol(currentCol, min);
        }
        return ans;
    }

    private void initializeCurrentCol(long[] currentCol, long min) {
        for (int i = 0; i < currentCol.length; i++) {
            currentCol[i] = currentCol[i] - min;
        }
    }

    private int getLeastBricks(long[] currentCol, long min) {
        int countOfNotMin = 0;
        for (long i : currentCol) {
            if (i != min)
                countOfNotMin++;
        }
        return countOfNotMin;
    }

    private void printArr(long[] arr) {
        System.out.print("print arr: ");
        for (long i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<List<Integer>> wall = new ArrayList<>();
        // int[][] wallArr = new int[][] {{1},{1},{1}};
        // int[][] wallArr = new int[][] {{1,2,2,1},{3,1,2},{1,3,2},{2,4},{3,1,2},{1,3,1,1}};
        // int[][] wallArr = new int[][] {{1,2,2,1},{3,1,2}};
        // int[][] wallArr = new int[][] {{Integer.MAX_VALUE, Integer.MAX_VALUE - 2, 2}, {Integer.MAX_VALUE, Integer.MAX_VALUE - 1, 1}, {Integer.MAX_VALUE - 1, Integer.MAX_VALUE, 1}};
        int max = Integer.MAX_VALUE;
        int[][] wallArr = new int[][] {
            {max,max,max,max,1,2},
            {2,max,max,max,max,1}
        };
        for (int[] arr : wallArr) {
            List<Integer> row = new ArrayList<>();
            for (int i : arr) {
                row.add(i);
            }
            wall.add(row);
        }
        System.out.println("test: "+ sol.leastBricks(wall));
    }
}