import java.util.Arrays;

class SolutionDP {
    private static final int INF = 1001;
    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        int[] counter = new int[26];
        for (int i = 0; i < n; i++) {
            Arrays.fill(counter, 0);
            int k = 0;
            int maxCount = 0;
            for (int j = i; j >= 0; j--) {
                int charVal = s.charAt(j) - 'a';
                if (counter[charVal] == 0) k++;
                counter[charVal]++;
                maxCount = Math.max(maxCount, counter[charVal]);
                if (k * maxCount == (i - j + 1)) {
                    // is balanced
                    dp[i + 1] = Math.min(dp[i + 1], dp[j] + 1);
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        SolutionDP sol = new SolutionDP();
        // String s = "fabccddg"; // res: 3
        String s = "abababaccddb"; // res: 2
        System.out.println("test: " + sol.minimumSubstringsInPartition(s));
    }
}