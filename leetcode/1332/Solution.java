class Solution {
    public int removePalindromeSub(String s) {
        int n = s.length();
        int l = 0;
        int r = n-1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return 2;
            }
            l++;
            r--;
        }
        return 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "ababa";
        System.out.println("test: " + sol.removePalindromeSub(s));
    }
}