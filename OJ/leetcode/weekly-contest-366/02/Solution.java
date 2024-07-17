import java.util.Arrays;
import java.util.List;

class Solution {
    public int minProcessingTime(List<Integer> processorTime, List<Integer> tasks) {
        int n = processorTime.size();
        // 逆序
        tasks.sort((o1, o2) -> o2.compareTo(o1));
        // 正序
        processorTime.sort((o1, o2) -> o1.compareTo(o2));
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int startTime = processorTime.get(i);
            int res = 0;
            for (int j = 0; j < 4; j++) {
                int currentTask = tasks.get(i * 4 + j);
                res = Math.max(res, startTime + currentTask);
            }
            ans = Math.max(ans, res);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<Integer> processorTime = Arrays.asList(10,20);
        List<Integer> tasks = Arrays.asList(2,3,1,2,5,8,4,3);
        System.out.println("test: " + sol.minProcessingTime(processorTime, tasks));
    }
}