import java.util.Arrays;

class Solution {
    public String sortSentence(String s) {

        String[] strs = new String[10];
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (curChar >= 49 && curChar <= 57) {
                // is number
                index = curChar - 48;
                if (i == s.length()-1) {
                    // is space
                    strs[index] = sb.toString();
                    index = 0;
                    sb = new StringBuilder();
                }
            } else {
                // is letter
                if (curChar == ' ' || i == s.length()-1) {
                    // is space
                    strs[index] = sb.toString();
                    index = 0;
                    sb = new StringBuilder();
                } else {
                    sb.append(curChar);
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        Arrays.stream(strs).forEach(a -> {
            if (a != null) {
                ans.append(a).append(" ");
            }
        });
        ans.replace(ans.length()-1, ans.length(), "");

        return ans.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "Myself2 Me1 I4 and3";
        System.out.println("test: " + sol.sortSentence(s));
    }
}