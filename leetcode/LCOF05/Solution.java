class Solution {
    private static final char SPACE = ' ';
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            if (cur == SPACE) {
                sb.append("%20");
            } else {
                sb.append(cur);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "We are happy.";
        System.out.println("test: " + sol.replaceSpace(s));
    }
}