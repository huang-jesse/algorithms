import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] preLeftTop = new int[m+1][n+1];
        System.out.println();
        List<Integer> results = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                preLeftTop[i][j] = preLeftTop[i][j-1] ^ preLeftTop[i-1][j] ^ preLeftTop[i-1][j-1] ^ matrix[i-1][j-1];
                results.add(preLeftTop[i][j]);
            }
        }
        Collections.sort(results, Comparator.reverseOrder());

        return results.get(k-1);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] matrix = new int[][] {{10,9,5},{2,0,4},{1,0,9},{3,4,8}};
        int k = 10;
        System.out.println("test: " + sol.kthLargestValue(matrix, k));
    }
}