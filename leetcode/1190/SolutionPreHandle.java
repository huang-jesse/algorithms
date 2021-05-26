import java.util.ArrayDeque;
import java.util.Deque;

class SolutionPreHandle {
    public String reverseParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        StringBuffer sb = new StringBuffer();
        int n = s.length();
        int[] pairs = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                int right = i;
                int left = stack.pop();
                pairs[left] = right;
                pairs[right] = left;
            }
        }

        int index = 0;
        // 1 means toward to right, 0 means toward to left
        int step = 1;
        while (index < n) {
            char cur = s.charAt(index);
            if (cur == '(' || cur == ')') {
                index = pairs[index];
                step = -step;// 1 -> 0, 0 -> 1
            } else {
                sb.append(cur);
            }
            index += step;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SolutionPreHandle sol = new SolutionPreHandle();
        String s = "a(ed(et(oc))el)c";
        System.out.println("test: " + sol.reverseParentheses(s));
    }
}