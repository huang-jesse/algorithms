import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    private static final int INF = 0x3fffffff;
    private static final char ZERO = '0';
    private static final char TWO = '2';
    private static final char FIVE = '5';
    private static final char SEVEN = '7';
    public int minimumOperations(String num) {
        int n = num.length();
        // 5 - > 25, 75
        int fiveOperation = find(num, FIVE, new HashSet<>(Arrays.asList(TWO, SEVEN)));
        // 0 -> 50, 00, or only 0
        int zeroOperation = find(num, ZERO, new HashSet<>(Arrays.asList(FIVE, ZERO)));
        int res = Math.min(n, Math.min(fiveOperation, zeroOperation));
        return res;
    }

    private int find(String num, char lastChar, Set<Character> preSet) {
        int n = num.length();
        // find lastChar
        int lastIndex = -1;
        for (int i = n - 1; i >= 0; i--) {
            char cur = num.charAt(i);
            if (cur == lastChar) {
                lastIndex = i;
                break;
            }
        }
        // not find
        if (lastIndex < 0) return INF;

        // find preSet
        int preIndex = -1;
        for (int i = lastIndex - 1; i >= 0; i--) {
            char cur = num.charAt(i);
            if (preSet.contains(cur)) {
                preIndex = i;
                break;
            }
        }
        // not find
        if (preIndex < 0) {
            if (lastChar == ZERO) {
                // special case for only 0
                return n - 1;
            } else {
                return INF;
            }
        };
        // min operation
        return (n - 1 - lastIndex) + (lastIndex - 1 - preIndex);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String num = "2908305"; // 3
        System.out.println("test: " + sol.minimumOperations(num));
    }
}