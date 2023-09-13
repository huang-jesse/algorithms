class Solution {
    public boolean checkValidGrid(int[][] grid) {
        // 必须从左上角出发
        if (grid[0][0] != 0) {
            return false;
        }
        int n = grid.length;
        int[][] moves = new int[n * n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                moves[grid[i][j]] = new int[]{i, j};
            }
        }
        // 模拟
        for (int i = 1; i < n * n; i++) {
            int distanceX = Math.abs(moves[i][0] - moves[i - 1][0]);
            int distanceY = Math.abs(moves[i][1] - moves[i - 1][1]);
            if (!(distanceX == 1 && distanceY == 2) && !(distanceX == 2 && distanceY == 1)) {
                // 不合法的移动
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{0,3,6},{5,8,1},{2,7,4}};
        System.out.println("test: " + sol.checkValidGrid(grid));
    }
}