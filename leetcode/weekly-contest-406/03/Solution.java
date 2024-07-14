import java.util.Arrays;

class Solution {
    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);
        int i = horizontalCut.length - 1;
        int j = verticalCut.length - 1;
        int h = 1;
        int v = 1;
        long ans = 0;
        while (i >= 0 && j >= 0) {
            if (horizontalCut[i] >= verticalCut[j]) {
                ans += h * horizontalCut[i];
                i--;
                v++;
            } else {
                ans += v * verticalCut[j];
                j--;
                h++;
            }
        }
        while (i >= 0) {
            ans += h * horizontalCut[i];
            i--;
        }
        while (j >= 0) {
            ans += v * verticalCut[j];
            j--;
        }
        return (int)ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int m = 3;
        int n = 2;
        int[] horizontalCut = {1,3};
        int[] verticalCut = {5};
        System.out.println("test: " + sol.minimumCost(m, n, horizontalCut, verticalCut));
    }
}