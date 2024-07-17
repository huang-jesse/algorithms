import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        // 以左端点从小到大排序
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int n = queries.length;
        // 排序查询数组
        Integer[] queryIdxs = new Integer[n];
        for (int i = 0; i < n; i++) {
            queryIdxs[i] = i;
        }
        Arrays.sort(queryIdxs, (o1, o2) -> queries[o1] - queries[o2]);

        // 按照区间间隔正序排序最小堆（即最小区间间隔在队首）
        PriorityQueue<int[]> intervalPq = new PriorityQueue<int[]>((o1, o2) -> o1[0] - o2[0]);
        int[] res = new int[n];
        // 从左往右遍历区间数组
        int m = intervals.length;
        int i = 0;
        for (int queryIdx : queryIdxs) {
            int query = queries[queryIdx];
            // 左端点符合查询条件则入队
            while (i < m && intervals[i][0] <= query) {
                intervalPq.offer(new int[]{intervals[i][1] - intervals[i][0] + 1, intervals[i][0], intervals[i][1]});
                i++;
            }
            // 右端点小于当前查询值则出队（也将大于接下来的查询）
            while (!intervalPq.isEmpty() && intervalPq.peek()[2] < query) {
                intervalPq.poll();
            }
            int ans = -1; // 队列为空则说明没有答案
            if (!intervalPq.isEmpty()) {
                // 当前队首即为查询的答案
                ans = intervalPq.peek()[0];
            }
            res[queryIdx] = ans;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] minInterval = {{1,4},{2,4},{3,6},{4,4}};
        int[] queries = {2,3,4,5};
        System.out.println("test: " + Arrays.stream(sol.minInterval(minInterval, queries)).boxed().collect(Collectors.toList()));
    }
}