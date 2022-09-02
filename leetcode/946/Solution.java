import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = pushed.length;
        for (int i = 0, j = 0; i < n; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,3,5,1,2};
        // int[] pushed = {1,2,3,4,5};
        // int[] popped = {4,5,3,2,1};
        System.out.println("test: " + sol.validateStackSequences(pushed, popped));
    }
}