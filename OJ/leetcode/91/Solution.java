class Solution {
    int n;
    Integer[] memo;
    public int numDecodings(String s) {
        // ASCII 0~9 -> 48~57
        if (s.charAt(0) == '0') return 0;
        n = s.length();
        memo = new Integer[n];

        return decodings(s, 0);
    }

    private int decodings(String s, int start) {
        if (start >= n ) return 1;
        if (s.charAt(start) == '0') return 0;
        if (memo[start] != null) {
            return memo[start];
        } 

        int res = decodings(s, start+1);
        if (start < n-1 && canCompositeDecode(s, start)) {
            res += decodings(s, start+2);
        }
        memo[start] = res;
        return res;
    }

    private boolean canCompositeDecode(String s, int index) {
        return s.charAt(index) == '1' || (s.charAt(index) == '2' && s.charAt(index+1) <= '6');
    }

    public static void main(String[] args) {
        String s = "1210451";
        Solution sol = new Solution();
        System.out.println("strStr: "+ sol.numDecodings(s));
    }
}