import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<String> ans;
    public List<String> letterCasePermutation(String s) {
        this.ans = new ArrayList<>();
        backtrack(s, 0, new StringBuilder());
        return ans;
    }

    private void backtrack(String s, int index, StringBuilder sb) {
        int n = s.length();
        if (index == n) {
            ans.add(sb.toString());
            return;
        }
        char cur = s.charAt(index);
        sb.append(cur);
        backtrack(s, index + 1, sb);
        sb.deleteCharAt(index);

        if (!Character.isDigit(cur)) {
            if (Character.isLowerCase(cur)) {
                cur = Character.toUpperCase(cur);
            } else {
                cur = Character.toLowerCase(cur);
            }
            sb.append(cur);
            backtrack(s, index + 1, sb);
            sb.deleteCharAt(index);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "a1b2";
        System.out.println("test: " + sol.letterCasePermutation(s));
    }
}