class Solution {
    private static final double PRECISION = 1e-6;
    static class Point {
        double x;
        double y;
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Vector {
        double x;
        double y;
        Vector(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public int numPoints(int[][] darts, int r) {
        int n = darts.length;
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(darts[i][0], darts[i][1]);
        }
        int res = Math.min(1, n);
        for (Point a : points) {
            for (Point b : points) {
                double ds = distance(a, b);
                if (ds == 0.0 || (ds > 2 * r && Math.abs(ds - 2 * r) >= PRECISION)) continue; // 没有圆心
                Point center = calculateCenter(a, b, r);
                res = Math.max(res, pointCount(center, r, points));
            }
        }
        return res;
    }

    private int pointCount(Point center, double r, Point[] points) {
        int count = 0;
        for (Point a : points) {
            double ds = distance(a, center);
            if (ds <= r || Math.abs(ds - r) <= PRECISION) {
                count++;
            }
        }
        return count;
    }

    private Point calculateCenter(Point a, Point b, int r) {
        // 计算中点
        Point m = new Point((a.x + b.x) / 2.0, (a.y + b.y) / 2.0);
        // 计算 ab 两点分别到中点 m 的距离()
        double d = distance(a, m);
        // 计算圆心 o 到 ab 中点 m 的垂线 mo 的高度 h
        double h = Math.sqrt((double)r * r - d * d);
        // ab 向量
        Vector abVector = new Vector(b.x - a.x, b.y - a.y);
        // mo 向量，由于 abVector 与 moVector 的点积为 0 所以为了简化计算，选择 abVector 的其中一个分量来获取一种任意垂直于 abVector 的一个向量 moVector
        Vector moVector = new Vector(-abVector.y, abVector.x);
        // 求 moVector 的单位向量
        double verctorLen = Math.sqrt(moVector.x * moVector.x + moVector.y * moVector.y);
        Vector unitMoVector = new Vector(moVector.x / verctorLen, moVector.y / verctorLen);
        // 获取真正的 moVector 向量（从 m 指向圆心 o 且长度为 h）
        moVector = new Vector(unitMoVector.x * h, unitMoVector.y * h);
        // 向量 m + mo 即求得圆心向量（坐标中心 0,0 指向圆心点 o 即点 o 的实际坐标）
        return new Point(m.x + moVector.x, m.y + moVector.y);
    }

    private double distance(Point a, Point b) {
        return Math.sqrt((b.y - a.y) * (b.y - a.y) + (b.x - a.x) * (b.x - a.x));
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[][] darts = {{-3,0},{3,0},{2,6},{5,4},{0,9},{7,8}};
        // int r = 5; // 5
        int[][] darts = {{-2,0},{2,0},{0,2},{0,-2}};
        int r = 2; // 4
        System.out.println("test: " + sol.numPoints(darts, r));
    }
}