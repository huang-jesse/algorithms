class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= l; i++) {
            int[] zerosOnes = getZerosOnes(strs[i-1]);
            int zeros = zerosOnes[0];
            int ones = zerosOnes[1];
            for (int j = m; j >= zeros; j--) {
                for (int k = n; k >= ones; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j-zeros][k-ones] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private int[] getZerosOnes(String str) {
        int[] zerosOnes = new int[2];
        for (int i = 0; i < str.length(); i++) {
            zerosOnes[str.charAt(i) - '0']++;
        }
        return zerosOnes;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        System.out.println("test: " + sol.findMaxForm(strs, m, n));
    }
}