import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    private int n;
    private int k;
    private int[] factorial;
    private Set<String> vis = new HashSet<>();
    public long countGoodIntegers(int n, int k) {
        this.n = n;
        this.k = k;
        this.factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        return backtrack(0, new int[(n + 1) / 2]);
    }

    private long backtrack(int index, int[] halfDigits) {
        // 左半边的回文数已经填满，右半边直接倒序复制即可
        if (index == (n + 1) / 2) return calculatePermutations(halfDigits);
        long res = 0;
        // 第一位不能选 0
        int low = index == 0 ? 1 : 0;
        for (int d = low; d <= 9; d++) {
            halfDigits[index] = d;
            res += backtrack(index + 1, halfDigits);
        }
        return res;
    }

    private long calculatePermutations(int[] halfDigits) {
        // 校验回文数是否可以被 k 整除，即是否为 k 回文整数
        int m = halfDigits.length;
        int[] counter = new int[10];
        char[] sortedHalfDigits = new char[n];
        long num = 0;
        int j = 0;
        for (int i = 0; i < m; i++) {
            num = num * 10 + halfDigits[i];
            counter[halfDigits[i]]++;
            sortedHalfDigits[j++] = (char)('0' + halfDigits[i]);
        }
        // 补全右半侧的数位，奇数还多了中间的一位，不需要补全
        int start = n % 2 == 1 ? m - 2 : m - 1;
        for (int i = start; i >= 0; i--) {
            num = num * 10 + halfDigits[i];
            counter[halfDigits[i]]++;
            sortedHalfDigits[j++] = (char)('0' + halfDigits[i]);
        }
        if (num % k != 0) {
            // 不能被 k 整除
            return 0;
        }
        Arrays.sort(sortedHalfDigits);
        String sortedHalfDigitsStr = new String(sortedHalfDigits);
        if (!vis.add(sortedHalfDigitsStr)) {
            // 该排列已经统计过了，不能重复统计
            return 0;
        }
        // 能被 k 整除，根据 k 回文数的 n 个数位，计算排列数
        long res = (long)(n - counter[0]) * factorial[n - 1];
        for (int c : counter) {
            res /= factorial[c];
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 5;
        int k = 6; // ans = 2468
        System.out.println("test: " + sol.countGoodIntegers(n, k));
    }
}