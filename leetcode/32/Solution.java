import java.util.Deque;
import java.util.LinkedList;

class Solution {
    private static final char LEFT_PARENTHESES = '(';
    public int longestValidParentheses(String s) {
        int n = s.length();
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == LEFT_PARENTHESES) {
                // '('
                stack.push(i);
            } else {
                // ')'
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = ")()())";
        // String s = "()(()";
        System.out.println("test: " + sol.longestValidParentheses(s));
    }
}