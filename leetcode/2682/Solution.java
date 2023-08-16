import java.util.Arrays;

class Solution {
    public int[] circularGameLosers(int n, int k) {
        boolean[] visit = new boolean[n];
        int count = 0;
        for (int i = k, j = 0; !visit[j]; i += k) {
            visit[j] = true;
            j = (j + i) % n;
            count++;
        }
        int[] ans = new int[n - count];
        for (int i = 0, j = 0; i < n; i++) {
            if (!visit[i]) {
                ans[j++] = i + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 5;
        int k = 2;
        // int n = 1;
        // int k = 1;
        System.out.println("test: " + Arrays.toString(sol.circularGameLosers(n, k)));
    }
}