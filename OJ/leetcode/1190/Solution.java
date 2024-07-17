import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String reverseParentheses(String s) {
        Deque<String> stack = new ArrayDeque<>();
        StringBuffer sb = new StringBuffer();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            if (cur == '(') {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if (cur ==')') {
                sb.reverse();
                sb.insert(0, stack.pop());
            } else {
                sb.append(cur);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "a(ed(et(oc))el)c";
        System.out.println("test: " + sol.reverseParentheses(s));
    }
}