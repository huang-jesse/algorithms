import java.util.Arrays;

class Solution {
    public int[] evenOddBit(int n) {
        String bitStr = Integer.toBinaryString(n);
        int m = bitStr.length();
        int[] ans = {0, 0};
        for (int i = 0; i < m; i++) {
            int bit = bitStr.charAt(m - 1 - i) - '0';
            if (bit == 1) {
                if (i % 2 == 0) {
                    // even
                    ans[0]++;
                } else {
                    // odd =
                    ans[1]++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 50; // binary: 110010
        System.out.println("test: " + Arrays.toString(sol.evenOddBit(n))); // ans = [1,2]
    }
}