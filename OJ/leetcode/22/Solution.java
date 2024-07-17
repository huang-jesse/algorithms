import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<String> ans = new ArrayList<>();
    private final static char LEFT = '(';
    private final static char RIGHT = ')';
    public List<String> generateParenthesis(int n) {
        backtrack(0, 0, 2*n, new StringBuilder());
        return ans;
    }

    private void backtrack(int i, int count, int len, StringBuilder sb) {
        if (i == len) {
            ans.add(sb.toString());
            return;
        }
        int midLimit = (len - 1 - i) + 1;
        if (count >= 0 && count < midLimit) {
            sb.append(LEFT);
            backtrack(i+1, count+1, len, sb);
            sb.deleteCharAt(i);
        }
        if (count > 0) {
            sb.append(RIGHT);
            backtrack(i+1, count-1, len, sb);
            sb.deleteCharAt(i);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        System.out.println("test: " + sol.generateParenthesis(n));
    }
}