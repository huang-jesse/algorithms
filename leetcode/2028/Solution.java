import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int total = (n + m) * mean;
        int totalOfM = Arrays.stream(rolls).sum();
        int totalOfN = total - totalOfM;
        if (totalOfN > 6*n || totalOfN < n) {
            return new int[]{};
        }
        int meanOfN = totalOfN / n;
        int remainderOfN = totalOfN % n;
        int[] ans = new int[n];
        for (int i = 0; i < remainderOfN; i++) {
            ans[i] = meanOfN + 1;
        }
        for (int i = remainderOfN; i < n; i++) {
            ans[i] = meanOfN;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] rolls = {3,2,4,3};
        int mean = 4;
        int n = 2;
        int[] ansArr = sol.missingRolls(rolls, mean, n);
        System.out.println("test: " + Arrays.stream(ansArr).boxed().collect(Collectors.toList()));
    }
}