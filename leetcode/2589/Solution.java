import java.util.Arrays;

class Solution {
    public int findMinimumTime(int[][] tasks) {
        int n = tasks.length;
        Arrays.sort(tasks, (o1, o2) -> o1[1] - o2[1]);
        int maxTime = tasks[n - 1][1];
        boolean[] ran = new boolean[maxTime + 1];
        int ans = 0;
        for (int[] task : tasks) {
            int start = task[0];
            int end = task[1];
            int duration = task[2];
            for (int i = start; i <= end; i++) {
                if (ran[i]) {
                    // this time the computer is runing
                    duration--;
                }
            }
            if (duration == 0) continue;
            // run computer from end to start, then the next task would be beneficial
            for (int i = end; duration > 0; i--) {
                if (!ran[i]) {
                    ran[i] = true;
                    duration--;
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[][] tasks = {{1,3,2},{2,5,3},{5,6,2}};
        int[][] tasks = {{2,3,1},{4,5,1},{1,5,2}};
        System.out.println("test: " + sol.findMinimumTime(tasks));
    }
}