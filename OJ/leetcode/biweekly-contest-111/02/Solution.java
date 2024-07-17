class Solution {
    public boolean canMakeSubsequence(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        if (n < m) {
            return false;
        }
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (j == m) {
                break;
            }
            char charOne = str1.charAt(i);
            char charSecond = (char)(((charOne - 'a' + 1) % 26) + 'a');
            char char2 = str2.charAt(j);
            if (charOne == char2 || charSecond == char2) {
                j++;
            }
        }
        return j == m;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String str1 = "dm";
        String str2 = "e";
        // String str1 = "abc";
        // String str2 = "ad";
        System.out.println("test: " + sol.canMakeSubsequence(str1, str2));
    }
}