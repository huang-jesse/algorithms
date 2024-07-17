class Solution {
    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        if (n % 2 == 0) {
            sb.append("a".repeat(n-1)).append("b");
        } else {
            sb.append("a".repeat(n));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        System.out.println("test: " + sol.generateTheString(n));
    }
}