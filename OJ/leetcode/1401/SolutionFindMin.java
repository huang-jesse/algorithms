/**
 * Locate the closest point of the square to the circle, you can then find the distance from this point to the center of the circle and check if this is less than or equal to the radius.
 */
class SolutionFindMin {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find min{|x - xCenter|} and min{|y - yCenter|} when x1 <= x <= x2, y1 <= y <= y2
        // then the point (x, y) of rectangle is the shortest distance from the center of circle
        int minXDiff = findMin(x1, x2, xCenter);
        int minYDiff = findMin(y1, y2, yCenter);
        return minXDiff * minXDiff + minYDiff * minYDiff <= radius *radius;
    }

    private int findMin(int i, int j, int k) {
        if (i <= k && k <= j) {
            return 0;
        }
        return k < i ? i - k : k - j;
    }

    public static void main(String[] args) {
        SolutionFindMin sol = new SolutionFindMin();
        int radius = 1;
        int xCenter = 0;
        int yCenter = 0;
        int x1 = 1;
        int y1 = -1;
        int x2 = 3;
        int y2 = 1;
        System.out.println("test: " + sol.checkOverlap(radius, xCenter, yCenter, x1, y1, x2, y2));
    }
}