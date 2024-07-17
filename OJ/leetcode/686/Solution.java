class Solution {
    public int repeatedStringMatch(String a, String b) {
        int m = a.length();
        int n = b.length();
        StringBuilder sb = new StringBuilder();
        int ans = 0;
        while (sb.length() < n) {
            sb.append(a);
            ans++;
        }
        sb.append(a);
        int index = sb.indexOf(b);
        if (index == -1) {
            return -1;
        }
        if (index + (n-1) >= m * ans) {
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String a = "abcd";
        String b = "cdabcdab";
        System.out.println("test: " + sol.repeatedStringMatch(a, b));
    }
}