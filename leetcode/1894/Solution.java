import java.util.Arrays;

class Solution {
    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        long sum = Arrays.stream(chalk).boxed().map((num) -> (long)num).reduce(0L, Long::sum);
        if (k > sum) {
            k = k % (int)sum;
        }
        for (int i = 0; i < n; i++) {
            int curChalk = chalk[i];
            k -= curChalk;
            if (k < 0) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] chalk = {5,1,5};
        int k = 22;
        System.out.println("test: " + sol.chalkReplacer(chalk, k));
    }
}