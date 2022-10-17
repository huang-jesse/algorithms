class Solution {
    public int longestContinuousSubstring(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int ans = 1;
        for (int i = 1, count = 1; i < n; i++) {
            int cur = chars[i] - 'a';
            int pre = chars[i - 1] - 'a';
            if (cur == pre + 1) {
                count++;
            } else {
                count = 1;
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "abcdeaassss";
        System.out.println("test: " + sol.longestContinuousSubstring(s));
    }
}