class Solution {
    public String gcdOfStrings(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        String ans = "";
        for (int len = 1; len <= Math.min(n, m); len++) {
            if (n % len != 0 || m % len != 0) {
                continue;
            }
            String subStr = str1.substring(0, len);
            // Check
            if (check(subStr, str1) && check(subStr, str2)) {
                ans = subStr;
                continue;
            }
        }
        return ans;
    }

    private boolean check(String subStr, String str) {
        int times = str.length() / subStr.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(subStr);
        }
        return sb.toString().equals(str);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String str1 = "ABCABC";
        String str2 = "ABC";
        System.out.println("test: " + sol.gcdOfStrings(str1, str2));
    }
}