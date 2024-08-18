class Solution {
    private static final char ZERO = '0';
    public long[] countKConstraintSubstrings(String s, int k, int[][] queries) {
        int n = s.length();
        int m = queries.length;
        long[] ans = new long[m];
        char[] arr = s.toCharArray();

        return null;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "0001111";
        int k = 2;
        int[][] queries = {{0,6}};
        System.out.println("test: " + sol.countKConstraintSubstrings(s, k, queries));
    }
}