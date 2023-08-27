import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<int[]> intervalList = new ArrayList<>();
        int n = intervals.length;
        intervalList.add(new int[]{intervals[0][0], intervals[0][1]});
        for (int i = 1; i < n; i++) {
            int[] cur = intervals[i];
            int[] pre = intervalList.get(intervalList.size() - 1);
            if (pre[1] >= cur[0]) {
                pre[1] = Math.max(pre[1], cur[1]);
            } else {
                intervalList.add(new int[]{cur[0], cur[1]});
            }
        }
        return intervalList.stream().toArray(int[][]::new);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println("test: " + Arrays.stream(sol.merge(intervals)).map(o1 -> Arrays.stream(o1).boxed().collect(Collectors.toList())).collect(Collectors.toList()));
    }
}