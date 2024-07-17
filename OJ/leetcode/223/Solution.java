class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int aArea = getArea(ax1, ay1, ax2, ay2);
        int bArea = getArea(bx1, by1, bx2, by2);
        return aArea + bArea - computeCoverArea(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2);
    }

    private int getArea(int x1, int y1, int x2, int y2) {
        return (x2-x1) * (y2-y1);
    }

    public int computeCoverArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int xIntersection = getIntersection(ax1, ax2, bx1, bx2);
        int yIntersection = getIntersection(ay1, ay2, by1, by2);
        if (xIntersection > 0 && yIntersection > 0) {
            return xIntersection * yIntersection;
        } else {
            return 0;
        }
    }

    public int getIntersection(int aFrom, int aTo, int bFrom, int bTo) {
        if (aFrom >= bTo || bFrom >= aTo) {
            // not intersection
            return 0;
        }
        int start = aFrom > bFrom ? aFrom : bFrom;
        int end = aTo < bTo ? aTo : bTo;
        return end - start;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int ax1 = -3;
        int ay1 = 0;
        int ax2 = 3;
        int ay2 = 4;
        int bx1 = 0;
        int by1 = -1;
        int bx2 = 9;
        int by2 = 2;
        System.out.println("test: " + sol.computeArea(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2));
    }
}