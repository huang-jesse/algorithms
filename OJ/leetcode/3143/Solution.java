import java.util.Arrays;

class Solution {
    private static final char LETTER_A = 'a';
    public int maxPointsInsideSquare(int[][] points, String s) {
        int n = points.length;
        Integer[] indexes = new Integer[n];
        for (int i = 0; i < n; i++) indexes[i] = i;
        Arrays.sort(indexes, (o1, o2) -> maxXY(points[o1]) - maxXY(points[o2]));
        boolean[] tagsMarked = new boolean[26];
        int ans = 0;
        int lastCount = 0;
        int lastMaxXY = 0;
        for (int i = 0; i < n; i++) {
            int index = indexes[i];
            int[] point = points[index];
            int maxXY = maxXY(point);
            if (maxXY == lastMaxXY) {
                lastCount++;
            } else {
                lastCount = 1;
            }
            ans++;
            int tagIndex = s.charAt(index) - LETTER_A;
            if (tagsMarked[tagIndex]) {
                ans -= lastCount;
                break;
            }
            tagsMarked[tagIndex] = true;
            lastMaxXY = maxXY;
        }
        return ans;
    }

    private static int maxXY(int[] point) {
        return Math.max(Math.abs(point[0]), Math.abs(point[1]));
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] points = {{2,2},{-1,-2},{-4,4},{-3,1},{3,-3}};
        String s = "abdca";
        // int[][] points = {{-1,-4},{16,-8},{13,-3},{-12,0}};
        // String s = "abda";
        // int[][] points = {{1,-1}};
        // String s = "a";
        System.out.println("test: " + sol.maxPointsInsideSquare(points, s));
    }
}