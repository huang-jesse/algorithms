class Solution {
    public int maxScore(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int score = 0;
        if (chars[0] == '0') {
            score++;
        }
        for (int i = 1; i < n; i++) {
            score += chars[i] - '0';
        }
        int ans = score;
        for (int i = 1; i < n-1; i++) {
            if (chars[i] - '0' == 0) {
                score++;
            } else {
                score--;
            }
            ans = Math.max(ans, score);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "011101";
        // String s = "00";
        System.out.println("test: " + sol.maxScore(s));
    }
}