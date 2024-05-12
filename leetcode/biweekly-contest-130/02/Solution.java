import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
    public int maxPointsInsideSquare(int[][] points, String s) {
        int n = points.length;
        int[] maxAxis = new int[n];
        for (int i = 0; i < n; i++) {
            maxAxis[i] = Math.max(Math.abs(points[i][0]), Math.abs(points[i][1]));
        }
        Integer[] indexes = IntStream.range(0, n).boxed().toArray(Integer[]::new);
        Arrays.sort(indexes, (o1, o2) -> maxAxis[o1] - maxAxis[o2]);
        int ans = 0;
        Set<Character> set = new HashSet<>();
        int current = -1;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int index = indexes[i];
            ans++;
            char curChar = s.charAt(index);
            if (current == maxAxis[index]) {
                count++;
            } else {
                current = maxAxis[index];
                count = 1;
            }
            if (set.contains(curChar)) {
                ans -= count;
                break;
            }
            set.add(curChar);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] points = {{2,2},{-1,-2},{-4,4},{-3,1},{3,-3}};
        String s = "abdca";
        System.out.println("test: " + sol.maxPointsInsideSquare(points, s));
    }
}