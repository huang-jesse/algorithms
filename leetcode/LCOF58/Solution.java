class Solution {
    public String reverseLeftWords(String s, int k) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = k; i < n; i++) {
            sb.append(s.charAt(i));
        }
        for (int i = 0; i < k; i++) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "abcdefg";
        int k = 2;
        System.out.println("test: " + sol.reverseLeftWords(s, k));
    }
}