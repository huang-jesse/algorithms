import java.util.Arrays;

class SolutionMask {
    private static final int MASK = 0x55555555;
    public int[] evenOddBit(int n) {
        return new int[]{Integer.bitCount(MASK & n), Integer.bitCount((MASK << 1) & n)};
    }

    public static void main(String[] args) {
        SolutionMask sol = new SolutionMask();
        int n = 50; // binary: 110010
        System.out.println("test: " + Arrays.toString(sol.evenOddBit(n))); // ans = [1,2]
    }
}