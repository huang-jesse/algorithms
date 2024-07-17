class Solution {
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return a.length() >= b.length() ? a.length() : b.length();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String a = "aba";
        String b = "cdc";
        System.out.println("test: " + sol.findLUSlength(a, b));
    }
}