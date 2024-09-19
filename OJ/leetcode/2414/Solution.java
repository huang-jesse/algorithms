class Solution {
    public int longestContinuousSubstring(String s) {
        int n = s.length();
        int ans = 0;
        int curLen = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) - 1 == s.charAt(i - 1)) {
                curLen++;
            } else {
                curLen = 1;
            }
            ans = Math.max(ans, curLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "abacaba"; // res: 2
        System.out.println("test: " + sol.longestContinuousSubstring(s));
    }
}