import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 枚举对角线
 * https://leetcode.cn/problems/sort-the-matrix-diagonally/solutions/2760094/dui-jiao-xian-pai-xu-fu-yuan-di-pai-xu-p-uts8/
 */
class SolutionOptimization {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        // 枚举对角线 k，k = i - j
        // 1-n <= k <= m-1
        for (int k = 1 - n; k <= m - 1; k++) {
            int leftI = Math.max(0, k);
            int rightI = Math.min(m - 1, k + n - 1);
            int[] tempArr = new int[rightI - leftI + 1];
            for (int i = leftI; i <= rightI; i++) {
                int j = i - k;
                tempArr[i - leftI] = mat[i][j];
            }
            // 排序
            Arrays.sort(tempArr);
            // 重新设置对角线的值
            for (int i = leftI; i <= rightI; i++) {
                int j = i - k;
                mat[i][j] = tempArr[i - leftI];
            }
        }
        return mat;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[][] mat = {{3,3,1,1},{2,2,1,2},{1,1,1,2}};
        int[][] ans = sol.diagonalSort(mat);
        List<String> ansList = Arrays.stream(ans).map(o -> Arrays.toString(o)).collect(Collectors.toList());
        System.out.println("test: " + ansList);
    }
}