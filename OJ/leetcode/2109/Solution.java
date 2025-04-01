class Solution {
    private static final char SPACE = ' ';
    public String addSpaces(String s, int[] spaces) {
        int n = s.length();
        int m = spaces.length;
        char[] chars = new char[n + m];
        for (int i = 0; i < m; i++) {
            chars[spaces[i] + i] = SPACE;
        }
        int j = 0; // index of chars
        for (int i = 0; i < n; i++) {
            while (chars[j] == SPACE) {
                j++;
            }
            chars[j++] = s.charAt(i);
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "LeetcodeHelpsMeLearn";
        int[] spaces = {8,13,15}; // ans = "Leetcode Helps Me Learn"
        System.out.println("test: " + sol.addSpaces(s, spaces));
    }
}