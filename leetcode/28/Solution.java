class Solution {
    public int strStr(String haystack, String needle) {
        int n = needle.length();
        int h = haystack.length();
        if (n == 0) return 0;
        if (h == 0) return -1;

        for (int i = 0; i + n <= h; i++) {
            boolean matches = true;
            for (int j = 0; j < n; j++) {
                if (needle.charAt(j) != haystack.charAt(i+j)) {
                    matches = false;
                    break;
                }
            }
            if (matches) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String haystack = "mississippi";
        String needle = "issip";
        Solution sol = new Solution();
        System.out.println("strStr: "+ sol.strStr(haystack, needle));
    }
}