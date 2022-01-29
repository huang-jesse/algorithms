import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;
        int[][] ans = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (isWater[row][col] == 1) {
                    // water
                    queue.offer(new int[]{row, col});
                } else {
                    // land
                    ans[row][col] = -1;
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                addNexts(queue, ans, cur);
            }
        }
        return ans;
    }

    private void addNexts(Queue<int[]> queue, int[][] ans, int[] cur) {
        int m = ans.length;
        int n = ans[0].length;
        int row = cur[0];
        int col = cur[1];
        int nextHeiht = ans[row][col] + 1;
        // top, bottom, left, right
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int k = 0; k < 4; k++) {
            int[] dir = dirs[k];
            int i = row + dir[0];
            int j = col + dir[1];
            if (i >= 0 && i < m && j >= 0 && j < n &&
                ans[i][j] == -1) {
                ans[i][j] = nextHeiht;
                queue.add(new int[]{i, j});
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] isWater = {{0,0,1},{1,0,0},{0,0,0}};
        int[][] ansArr = sol.highestPeak(isWater);
        List<List<Integer>> ans = Arrays.stream(ansArr).map(arr -> Arrays.stream(arr).boxed().collect(Collectors.toList())).collect(Collectors.toList());
        System.out.println("test: " + ans);
    }
}