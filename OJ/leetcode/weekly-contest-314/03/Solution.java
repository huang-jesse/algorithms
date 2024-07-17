import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String robotWithString(String s) {
        int n = s.length();
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        int[] counter = new int[26];
        int min = 0;
        for (int i = 0; i < n; i++) {
            counter[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            while (min <= 25 && counter[min] == 0) {
                min++;
            }
            while (!stack.isEmpty() && stack.peek() - 'a' <= min) {
                sb.append(stack.pop());
            }
            stack.push(s.charAt(i));
            counter[s.charAt(i) - 'a']--;
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String s = "bacd"; // abcd
        String s = "bdda"; // addb
        System.out.println("test: " + sol.robotWithString(s));
    }
}