import java.util.Arrays;

class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] ans = new int[n];
        if (k == 0) {
            return ans;
        }
        int l = k > 0 ? 1 : n + k;
        int r = k > 0 ? k : n - 1;
        int sum = 0;
        for (int i = l; i <= r; i++) {
            sum += code[i];
        }
        for (int i = 0; i < n; i++) {
            ans[i] = sum;
            sum -= code[l];
            l = (l + 1) % n;
            r = (r + 1) % n;
            sum += code[r];
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] code = {5,7,1,4};
        // int k = 3;
        int[] code = {2,4,9,3};
        int k = -2;
        System.out.println("test: " + Arrays.toString(sol.decrypt(code, k)));
    }
}