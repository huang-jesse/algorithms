import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        Deque<int[]> stack = new ArrayDeque<>();
        boolean isInserted = false;
        int n = intervals.length;
        int i = 0;
        while (i < n) {
            int[] interval = intervals[i];
            if (!isInserted) {
                if (newInterval[1] < interval[0]) {
                    // newInterval 直接插入
                    stack.push(new int[]{newInterval[0], newInterval[1]});
                    isInserted = true;
                    continue;
                }
                if (interval[1] < newInterval[0]) {
                    stack.push(new int[]{interval[0], interval[1]});
                } else {
                    // interval[1] >= newInterval[0]
                    stack.push(new int[]{Math.min(interval[0], newInterval[0]), Math.max(interval[1], newInterval[1])});
                    isInserted = true;
                }
            } else {
                // 已插入 newInterval
                if (interval[0] <= stack.peek()[1]) {
                    stack.peek()[1] = Math.max(interval[1], stack.peek()[1]);
                } else {
                    // interval[0] > stack.peek()[1]
                    stack.push(new int[]{interval[0], interval[1]});
                }
            }
            i++;
        }
        if (!isInserted) {
            stack.push(new int[]{newInterval[0], newInterval[1]});
        }
        int m = stack.size();
        int[][] ans = new int[m][2];
        for (int j = 0; j < m; j++) {
            ans[j] = stack.pollLast();
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {4,8};
        int[][] res = sol.insert(intervals, newInterval);
        System.out.println("test: " + Arrays.stream(res).map(arr -> Arrays.stream(arr).boxed().collect(Collectors.toList())).collect(Collectors.toList()));
    }
}