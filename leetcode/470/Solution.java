import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution {
    Random random = new Random();
    Supplier<IntStream> ins = () -> random.ints(1, 8);
    public int rand10() {
        int row, col, idx;
        do {
            row = rand7();
            col = rand7();
            idx = col + (row - 1) * 7;
        } while (idx > 40);
        return 1 + (idx - 1) % 10;
    }

    private int rand7() {
        return ins.get().findAny().getAsInt();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println("test: " + sol.rand10());
    }
}