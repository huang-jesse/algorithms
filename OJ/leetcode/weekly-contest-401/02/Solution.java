import java.util.Arrays;

class Solution {
    private static final int MOD = (int)1e9 + 7;
    public int valueAfterKSeconds(int n, int k) {
        int[] arr = new int[n];
        Arrays.fill(arr, 1);
        for (int i = 0; i < k;i++) {
            for (int j = 1; j < n; j++) {
                arr[j] = (arr[j] + arr[j - 1]) % MOD;
            }
        }
        return arr[n - 1];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 4;
        int k = 5;
        System.out.println("test: " + sol.valueAfterKSeconds(n, k));
    }
}