import java.util.Arrays;

class SolutionBinary {
    public int[] evenOddBit(int n) {
        int[] ans = {0, 0};
        for (int i = 0; n > 0; n >>= 1) {
            int bit = n & 1;
            ans[i] += bit;
            i ^= 1; // 切换奇偶
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionBinary sol = new SolutionBinary();
        int n = 50; // binary: 110010
        System.out.println("test: " + Arrays.toString(sol.evenOddBit(n))); // ans = [1,2]
    }
}