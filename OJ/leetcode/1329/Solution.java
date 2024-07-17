import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Solution {
    public int[][] diagonalSort(int[][] mat) {
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        int n = mat.length;
        int m = mat[0].length;
        for (int row = 0, col = 0; row < n; row++) {
            int matDiagonalLen = Math.min(n - row, m);
            for (int i = 0; i < matDiagonalLen; i++) {
                int current = mat[row + i][col + i];
                minPQ.offer(current);
            }
            // diagonal sort
            for (int i = 0; i < matDiagonalLen; i++) {
                mat[row + i][col + i] = minPQ.poll();
            }
        }

        for (int row = 0, col = 1; col < m; col++) {
            int matDiagonalLen = Math.min(m - col, n);
            for (int i = 0; i < matDiagonalLen; i++) {
                int current = mat[row + i][col + i];
                minPQ.offer(current);
            }
            // diagonal sort
            for (int i = 0; i < matDiagonalLen; i++) {
                mat[row + i][col + i] = minPQ.poll();
            }
        }
        return mat;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] mat = {{3,3,1,1},{2,2,1,2},{1,1,1,2}};
        int[][] ans = sol.diagonalSort(mat);
        List<String> ansList = Arrays.stream(ans).map(o -> Arrays.toString(o)).collect(Collectors.toList());
        System.out.println("test: " + ansList);
    }
}