import java.util.Arrays;

class Solution {
    public String orderlyQueue(String s, int k) {
        if (k <= 0) {
            return s;
        }
        if (k > 1) {
            char[] sChars = s.toCharArray();
            Arrays.sort(sChars);
            return new String(sChars);
        }

        String ans = s;
        StringBuilder sb = new StringBuilder(s);
        for (int i = 1; i < s.length(); i++) {
            char headChar = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(headChar);
            String nextString = sb.toString();
            if (nextString.compareTo(ans) < 0) {
                ans = nextString;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "baaca";
        int k = 3;
        System.out.println("test: " + sol.orderlyQueue(s, k));
    }
}