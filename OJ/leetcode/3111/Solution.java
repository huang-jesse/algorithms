import java.util.Arrays;

class Solution {
    public int minRectanglesToCoverPoints(int[][] points, int w) {
        Arrays.sort(points, (o1, o2) -> o1[0] -o2[0]);
        int count = 0;
        int rightLimit = -1;
        for (int[] point : points) {
            int x = point[0];
            if (rightLimit < x) {
                // new rectangle
                count++;
                rightLimit = x + w;
            }
            // covered by previous rectangle
        }
        return count;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] points = {{2,1},{1,0},{1,4},{1,8},{3,5},{4,6}};
        int w = 1;
        System.out.println("test: " + sol.minRectanglesToCoverPoints(points, w));
    }
}