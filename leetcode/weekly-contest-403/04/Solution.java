
class Solution {
    // private static final int INF = 0x3fffffff;
    public int minimumSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        // 分治法，划分三块矩形区域，接着计算三块区域中包含1的矩形的面积的和
        // 执行 上中下 划分，上左右划分，下左右划分
        int ans = calculateRectangles(grid);
        // 将整个 grid 旋转90度
        int[][] rotatedGrid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotatedGrid[i][j] = grid[n - 1 - j][i];
            }
        }
        // 执行 左中右 划分，左上下 划分，右上下 划分
        ans = Math.min(ans, calculateRectangles(rotatedGrid));
        return ans;
    }

    /**
     * 执行 上中下 划分，上左右划分，下左右划分
     * @param grid
     * @return
     */
    private int calculateRectangles(int[][] grid) {
        // 划分的区域中可能存在包含1矩形面积为0的区域，但是这种情况下的area()计算结果为将会是该划分矩形区域的总面积（不包含1）因此总体面积反而变大。
        // 因此总体不影响结果
        int res = umd(grid);
        res = Math.min(res, Math.min(ulr(grid), dlr(grid)));
        return res;
    }

    private int umd(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int res = n * m;
        // 划分为上中下三个区域
        for (int r1 = 1; r1 < n - 1; r1++) {
            int areaUp = area(grid, 0, r1, 0, m);
            for (int r2 = r1 + 1; r2 < n; r2++) {
                int areaMid = area(grid, r1, r2, 0, m);
                int areaBottom = area(grid, r2, n, 0, m);
                res = Math.min(res, areaUp + areaMid + areaBottom);
            }
        }
        return res;
    }

    private int ulr(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int res = n * m;
        // 划分为上左右三个区域
        for (int r1 = 1; r1 < n; r1++) {
            int areaUp = area(grid, 0, r1, 0, m);
            for (int c1 = 1; c1 < m; c1++) {
                int areaLeft = area(grid, r1, n, 0, c1);
                int areaRight = area(grid, r1, n, c1, m);
                res = Math.min(res, areaUp + areaLeft + areaRight);
            }
        }
        return res;
    }

    private int dlr(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int res = n * m;
        // 划分为下左右三个区域
        for (int r1 = n - 1; r1 > 0; r1--) {
            int areaDown = area(grid, r1, n, 0, m);
            for (int c1 = 1; c1 < m; c1++) {
                int areaLeft = area(grid, 0, r1, 0, c1);
                int areaRight = area(grid, 0, r1, c1, m);
                res = Math.min(res, areaDown + areaLeft + areaRight);
            }
        }
        return res;
    }

    /**
     * 计算包含1的矩形面积（如果该区域不包含1，则计算结果为该区域总面积）
     * @param grid
     * @param r1
     * @param r2 exclusive
     * @param c1
     * @param c2 exclusive
     * @return
     */
    private int area(int[][] grid, int r1, int r2, int c1, int c2) {
        int minR = r2;
        int maxR = r1;
        int minC = c2;
        int maxC = c1;
        for (int i = r1; i < r2; i++) {
            for (int j = c1; j < c2; j++) {
                if (grid[i][j] == 1) {
                    minR = Math.min(minR, i);
                    maxR = Math.max(maxR, i);
                    minC = Math.min(minC, j);
                    maxC = Math.max(maxC, j);
                }
            }
        }
        // 计算包含1的矩形的面积（如果该区域不包含1，则计算结果为该区域总面积）
        return (maxR - minR + 1) * (maxC - minC + 1);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{1,0,1,0}, {0,1,0,1}};
        System.out.println("test: " + sol.minimumSum(grid));
    }
}