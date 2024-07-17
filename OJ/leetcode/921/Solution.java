class Solution {
    private static final char OPEN_PAR = '(';
    public int minAddToMakeValid(String s) {
        int n = s.length();
        int ans = 0;
        int openCount = 0;
        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            if (cur == OPEN_PAR) {
                openCount++;
            } else {
                // cur is ')'
                if (openCount > 0) {
                    openCount--;
                } else {
                    ans++;
                }
            }
        }
        ans += openCount;
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "(((";
        System.out.println("test: " + sol.minAddToMakeValid(s));
    }
}