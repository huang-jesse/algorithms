import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int alternateDigitSum(int n) {
        Deque<Integer> stack = new ArrayDeque<>();
        while (n != 0) {
            stack.push(n % 10);
            n /= 10;
        }
        int ans = 0;
        int sign = 1;
        while (!stack.isEmpty()) {
            ans += stack.pop() * sign;
            sign = -sign;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 521;
        System.out.println("test: " + sol.alternateDigitSum(n));
    }
}