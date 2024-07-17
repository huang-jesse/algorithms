class Solution {
    public String smallestString(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int l = 0;
        while (l < n && chars[l] == 'a') {
            l++;
        }
        if (l == n) chars[n - 1] = 'z';
        while (l < n && chars[l] != 'a') {
            chars[l]--;
            l++;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String s = "leetcode"; // kddsbncd
        String s = "acbbc"; // abaab
        // String s = "aaa"; // aaz
        System.out.println("test: " + sol.smallestString(s));
    }
}