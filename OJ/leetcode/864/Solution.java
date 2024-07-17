import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final char START_SIGN = '@';
    private static final char WALL_SIGN = '#';
    public int shortestPathAllKeys(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();
        // {row, col, keys}
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][1 << 6];
        int keySize = 0;
        int[] keyToIndex = new int[26];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char cur = grid[i].charAt(j);
                if (cur == START_SIGN) {
                    queue.offer(new int[]{i, j, 0});
                    visited[i][j][0] = true;
                }
                if (Character.isLowerCase(cur)) {
                    keyToIndex[cur - 'a'] = keySize;
                    keySize++;
                }
            }
        }
        int allKeys = (1 << keySize) - 1;
        int ans = -1;
        boolean isFoundAllKeys = false;
        outer:while (!queue.isEmpty()) {
            ans++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int row = cur[0];
                int col = cur[1];
                int keys = cur[2];
                if (keys == allKeys) {
                    isFoundAllKeys = true;
                    break outer;
                }
                for (int[] direction : directions) {
                    int nextRow = direction[0] + row;
                    int nextCol = direction[1] + col;
                    int nextKeys = keys;
                    if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) {
                        continue;
                    }
                    char nextChar = grid[nextRow].charAt(nextCol);
                    if (visited[nextRow][nextCol][nextKeys] || nextChar == WALL_SIGN) {
                        continue;
                    }
                    int requiredKey = Character.toLowerCase(nextChar) - 'a';
                    if (Character.isUpperCase(nextChar) && (nextKeys & (1 << keyToIndex[requiredKey])) == 0) {
                        continue;
                    }
                    // add key
                    if (Character.isLowerCase(nextChar)) {
                        nextKeys = nextKeys | (1 << keyToIndex[nextChar - 'a']);
                    }
                    queue.offer(new int[]{nextRow, nextCol, nextKeys});
                    visited[nextRow][nextCol][nextKeys] = true;
                }
            }
        }
        return isFoundAllKeys ? ans : -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String[] grid = {"@.a..","###.#","b.A.B"};// 8
        String[] grid = {"@..aA","..B#.","....b"};// 6
        System.out.println("test: " + sol.shortestPathAllKeys(grid));
    }
}