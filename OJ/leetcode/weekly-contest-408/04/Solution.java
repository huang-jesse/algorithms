class Solution {
    private int[] fa;
    public boolean canReachCorner(int X, int Y, int[][] circles) {
        int n = circles.length;
        // all vertices: all circles: [0,1,...,n - 1] , and top-left: n, bottom-right: n + 1
        int topLeftVertex = n;
        int bottomRightVertex = n + 1;
        this.fa = new int[n + 2];
        for (int i = 0; i < n + 2; i++) fa[i] = i;
        for (int i = 0; i < n; i++) {
            int[] circle1 = circles[i];
            for (int j = i + 1; j < n; j++) {
                int[] circle2 = circles[j];
                // is circle1 and circle2 intersect or touch ?
                int xDiff = circle1[0] - circle2[0];
                int yDiff = circle1[1] - circle2[1];
                int rTot = circle1[2] + circle2[2];
                long dsPower = (long)xDiff * xDiff + (long)yDiff * yDiff;
                long rPower = (long)rTot * rTot;
                if (rPower >= dsPower) {
                    // intersect or touch
                    merge(j, i);
                }
            }
            // is circle1 and top-left edge intersect or touch ?
            if (isIntersectWithHLine(0, X, Y, circle1) || isIntersectWithVLine(0, Y, 0, circle1)) {
                merge(topLeftVertex, i);
            }

            // is circle1 and bottom-right intersect or touch ?
            if (isIntersectWithHLine(0, X, 0, circle1) || isIntersectWithVLine(0, Y, X, circle1)) {
                merge(bottomRightVertex, i);
            }
        }
        return find(topLeftVertex) != find(bottomRightVertex);
    }

    private boolean isIntersectWithHLine(int hx1, int hx2, int hy, int[] circle) {
        int x = circle[0];
        int y = circle[1];
        int r = circle[2];
        if (x >= hx1 && x <= hx2) {
            return Math.abs(hy - y) <= r;
        } else if (x < hx1) {
            int xDiff = hx1 - x;
            int yDiff = hy - y;
            return (long)xDiff * xDiff + (long)yDiff * yDiff <= (long)r * r;
        } else {
            // x > hx2
            int xDiff = hx2 - x;
            int yDiff = hy - y;
            return (long)xDiff * xDiff + (long)yDiff * yDiff <= (long)r * r;
        }
    }

    private boolean isIntersectWithVLine(int vy1, int vy2, int vx, int[] circle) {
        int x = circle[0];
        int y = circle[1];
        int r = circle[2];
        if (y >= vy1 && y <= vy2) {
            return Math.abs(vx - x) <= r;
        } else if (y < vy1) {
            int xDiff = vx - x;
            int yDiff = vy1 - y;
            return (long)xDiff * xDiff + (long)yDiff * yDiff <= (long)r * r;
        } else {
            // x > vy2
            int xDiff = vx - x;
            int yDiff = vy2 - y;
            return (long)xDiff * xDiff + (long)yDiff * yDiff <= (long)r * r;
        }
    }

    private int find(int x) {
        if (fa[x] == x) {
            return x;
        } else {
            fa[x] = find(fa[x]);
            return fa[x];
        }
    }

    /**
     * merge i to j
     * @param i
     * @param j
     * @return
     */
    private void merge(int i, int j) {
        fa[find(i)] = find(j);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int x = 3;
        // int y = 4;
        // int[][] circles = {{2,1,1}}; // true
        int x = 3;
        int y = 3;
        int[][] circles = {{2,1,1},{1,2,1}}; // false
        System.out.println("test: " + sol.canReachCorner(x, y, circles));
    }
}