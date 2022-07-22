import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        int n = intervals.length;
        int m = 2;
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });
        int res = 0;
        List<Integer>[] temp = new List[n];
        for (int i = 0; i < n; i++) {
            temp[i] = new ArrayList<>();
        }
        for (int i = n-1; i >= 0; i--) {
            for (int j = intervals[i][0], k = temp[i].size(); k < m; j++,k++) {
                res++;
                help(intervals, temp, i-1, j);
            }
        }
        return res;
    }

    private void help(int[][] intervals, List<Integer>[] temp, int pos, int num) {
        for (int i = pos; i >= 0; i--) {
            if (intervals[i][1] < num) {
                break;
            }
            temp[i].add(num);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] intervals = {{1, 3}, {1, 4}, {2, 5}, {3, 5}};
        System.out.println("test: " + sol.intersectionSizeTwo(intervals));
    }
}