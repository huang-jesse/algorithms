class Solution {
    private static final int R = 26;
    private static final char LETTER_A = 'a';
    public String getSmallestString(String s, int k) {
        if (k == 0) return s;
        int n = s.length();
        char[] t = s.toCharArray();
        int i = 0;
        while (i < n && k > 0) {
            char cur = t[i];
            int index = cur - LETTER_A;
            int dsToA = Math.min(index, R - index);
            if (k >= dsToA) {
                t[i] = LETTER_A;
                k -= dsToA;
            } else {
                t[i] = (char)(LETTER_A + (index - k));
                k = 0;
            }
            i++;
        }
        return new String(t);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "zbbz";
        int k = 3; // aaaz
        System.out.println("test: " + sol.getSmallestString(s, k));
    }
}