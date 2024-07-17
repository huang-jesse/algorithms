import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        int n = chars.length;
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = n-1; i >= 0; i--) {
            char cur = chars[i];
            if (cur == '*') {
                stack.push(cur);
            } else {
                if (stack.isEmpty()) {
                    sb.append(cur);
                } else {
                    stack.pop();
                }
            }
        }
        sb = sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "leet**cod*e";
        System.out.println("test: " + sol.removeStars(s));
    }
}