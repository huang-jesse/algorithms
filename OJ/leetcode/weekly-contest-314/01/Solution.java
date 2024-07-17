import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int hardestWorker(int n, int[][] logs) {
        int m = logs.length;
        int max = 0;
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int leaveTime = logs[i][1];
            int id = logs[i][0];
            if (i == 0) {
                max = leaveTime - 0;
                ids.add(id);
            } else {
                int preLeaveTime = logs[i - 1][1];
                int timeRange = leaveTime - preLeaveTime;
                if (timeRange > max) {
                    max = leaveTime - preLeaveTime;
                    ids.clear();
                    ids.add(id);
                } else if (timeRange == max) {
                    ids.add(id);
                }
            }
        }
        Collections.sort(ids);
        return ids.get(0);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 26;
        int[][] logs = {{1,1},{3,7},{2,12},{7,17}};
        System.out.println("test: " + sol.hardestWorker(n, logs));
    }
}