class Solution {
    public boolean isPrefixString(String s, String[] words) {
        int n = words.length;
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < n; i++) {
            sb.append(words[i]);
            String curString = sb.toString();
            if (curString.length() > len) {
                return false;
            }
            if (s.equals(sb.toString())) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "iloveleetcode";
        String[] words = {};
        System.out.println("test: " + sol.isPrefixString(s, words));
    }
}