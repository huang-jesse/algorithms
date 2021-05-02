import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SolutionMap {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (List<Integer> widths : wall) {
            int n = widths.size();
            int sum = 0;
            for (int i = 0; i < n - 1; i++) {
                sum += widths.get(i);
                cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
            }
        }
        int maxCnt = 0;
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            maxCnt = Math.max(maxCnt, entry.getValue());
        }
        return wall.size() - maxCnt;
    }

    public static void main(String[] args) {
        SolutionMap sol = new SolutionMap();
        List<List<Integer>> wall = new ArrayList<>();
        // int[][] wallArr = new int[][] {{1},{1},{1}};
        int[][] wallArr = new int[][] {{1,2,2,1},{3,1,2},{1,3,2},{2,4},{3,1,2},{1,3,1,1}};
        // int[][] wallArr = new int[][] {{Integer.MAX_VALUE, Integer.MAX_VALUE - 2, 2}, {Integer.MAX_VALUE, Integer.MAX_VALUE - 1, 1}, {Integer.MAX_VALUE - 1, Integer.MAX_VALUE, 1}};
        // int[][] wallArr = new int[][] {{1,2,2,1},{3,1,2}};
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