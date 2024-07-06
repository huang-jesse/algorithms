class Solution {
    private static final int INF = 0x3fffffff;
    public int minimumDistance(int[][] points) {
        int[] maxDistanceIndexes = findMaxDistanceTwoPoints(points);
        return Math.min(maxDistance(points, maxDistanceIndexes[0]), maxDistance(points, maxDistanceIndexes[1]));
    }

    private int maxDistance(int[][] points, int skipIndex) {
        int n = points.length;
        // x + y
        // {min, max}
        int[] minMax1 = new int[]{INF, -INF};
        // x - y
        // {min, max}
        int[] minMax2 = new int[]{INF, -INF};
        for (int i = 0; i < n; i++) {
            if (i == skipIndex) continue;
            int x = points[i][0];
            int y = points[i][1];
            int xy1 = x + y;
            minMax1[0] = Math.min(minMax1[0], xy1);
            minMax1[1] = Math.max(minMax1[1], xy1);
            int xy2 = x - y;
            minMax2[0] = Math.min(minMax2[0], xy2);
            minMax2[1] = Math.max(minMax2[1], xy2);
        }
        int distance1 = minMax1[1] - minMax1[0];
        int distance2 = minMax2[1] - minMax2[0];
        return Math.max(distance1, distance2);
    }

    private int[] findMaxDistanceTwoPoints(int[][] points) {
        int n = points.length;
        // x + y
        int[] minMax1 = new int[]{INF, -INF};
        int[] indexes1 = new int[]{-1, -1};
        // x - y
        int[] minMax2 = new int[]{INF, -INF};
        int[] indexes2 = new int[]{-1, -1};
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            int xy1 = x + y;
            if (xy1 < minMax1[0]) {
                minMax1[0] = xy1;
                indexes1[0] = i;
            }
            if (xy1 > minMax1[1]) {
                minMax1[1] = xy1;
                indexes1[1] = i;
            }
            int xy2 = x - y;
            if (xy2 < minMax2[0]) {
                minMax2[0] = xy2;
                indexes2[0] = i;
            }
            if (xy2 > minMax2[1]) {
                minMax2[1] = xy2;
                indexes2[1] = i;
            }
        }
        int distance1 = minMax1[1] - minMax1[0];
        int distance2 = minMax2[1] - minMax2[0];
        if (distance1 >= distance2) {
            return indexes1;
        } else {
            return indexes2;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[][] points = {{3,10},{5,15},{10,2},{4,4}}; // 12
        int[][] points = {{5,3},{4,6},{2,4},{1,8},{3,9},{1,6}}; // 6
        System.out.println("test: " + sol.minimumDistance(points));
    }
}