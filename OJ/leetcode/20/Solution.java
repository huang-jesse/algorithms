import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    Map<Character, Character> right2left = new HashMap<>(){{
        put(')','(');
        put(']','[');
        put('}','{');
    }};
    public boolean isValid(String s) {
        Deque<Character> leftStack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            Character cur = s.charAt(i);
            if (!right2left.containsKey(cur)) {
                leftStack.push(cur);
            } else {
                if (leftStack.isEmpty() || leftStack.pop() != right2left.get(cur)) {
                    return false;
                }
            }
        }
        return leftStack.isEmpty();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "{[]}";
        System.out.println("test: " + sol.isValid(s));
    }
}