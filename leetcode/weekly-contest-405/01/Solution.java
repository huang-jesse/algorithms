
class Solution {
    public String getEncryptedString(String s, int k) {
        int n = s.length();
        k %= n;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s.charAt((i + k) % n));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "dart";
        int k = 3;
        System.out.println("test: " + sol.getEncryptedString(s, k));
    }
}