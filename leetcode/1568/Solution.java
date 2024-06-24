class Solution {
    private int m;
    private int n;
    public int minDays(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        // 陆地原本就是分离的
        if (check(grid)) {
            return 0;
        }
        // 检查仅更改1格陆地能够将陆地分离
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) continue;
                // 将当前陆地1更改为水单元0
                grid[i][j] = 0;
                // 检查当前 grid 是否陆地分离
                if (check(grid)) {
                    return 1;
                }
                // 恢复
                grid[i][j] = 1;
            }
        }
        // 检查将指定1格陆地的周围陆地1更改为水单元0，能够将陆地分离
        // 最多2次就可以完成，因为总是有边缘的陆地1单元格，边缘的单元格最多2次就可以将其隔离。
        return 2;
    }

    /**
     * 检查当前 grid 是否陆地分离
     * @param grid
     * @return
     */
    private boolean check(int[][] grid) {
        int total = m * n;
        UnionFindPathCompression uf = new UnionFindPathCompression(total);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) continue;
                int curIndex = encode(i, j);
                if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                    int preIndex = encode(i - 1, j);
                    uf.merge(curIndex, preIndex);
                }
                if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                    int preIndex = encode(i, j - 1);
                    uf.merge(curIndex, preIndex);
                }
            }
        }
        return isDisconnected(grid, uf);
    }

    private boolean isDisconnected(int[][] grid, UnionFindPathCompression uf) {
        int root = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) continue;
                if (root == -1) {
                    root = uf.find(encode(i, j));
                }
                if (uf.find(encode(i, j)) != root) {
                    // 陆地已经是分离的了
                    return true;
                }
            }
        }
        if (root == -1) {
            // 没有陆地
            return true;
        }
        return false;
    }

    private int encode(int i, int j) {
        return i * this.n + j;
    }

    /**
     * 并查集-路径压缩版本
     */
    static class UnionFindPathCompression {
        private int[] fa;
        public UnionFindPathCompression(int n) {
            // init
            fa = new int[n];
            for (int i = 0; i < n; i++) {
                fa[i] = i;
            }
        }

        /**
         * Path compression
         * @param x
         * @return
         */
        public int find(int x) {
            if (fa[x] == x) {
                return x;
            } else {
                fa[x] = find(fa[x]);
                return fa[x];
            }
        }
        public void merge(int i, int j) {
            fa[find(i)] = find(j);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[][] grid = {{0,1,1,0}, {0,1,1,0}, {0,0,0,0}};
        int[][] grid = {{1,1,0,1,1},{1,1,1,1,1},{1,1,0,1,1},{1,1,0,1,1}};
        System.out.println("test: " + sol.minDays(grid));
    }
}