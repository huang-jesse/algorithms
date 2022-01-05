class Solution {
    private final static char WILDCARD = '?';
    private final static char LOWERCASE_A = 'a';
    private final static char LOWERCASE_C = 'c';
    public String modifyString(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            char cur = chars[i];
            if (cur != WILDCARD) {
                continue;
            }
            for (char ch = LOWERCASE_A; ch <= LOWERCASE_C; ch++) {
                if ((i > 0 && ch == chars[i-1])
                    || (i < n-1) && ch == chars[i+1]) {
                    continue;
                }
                chars[i] = ch;
                break;
            }
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "j?qg??b";
        System.out.println("test: " + sol.modifyString(s));
    }
}