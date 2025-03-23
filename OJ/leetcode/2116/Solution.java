import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public boolean canBeValid(String s, String locked) {
        int n = s.length();
        if (n % 2 != 0) return false;
        char[] status = locked.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (status[i] == '0') continue;
            // locked
            if (s.charAt(i) == '(') {
                stack.addFirst(i);
            } else {
                if (!stack.isEmpty()) {
                    stack.removeFirst();
                } else {
                    // closing parenthesis
                    status[i] = '4';
                }
            }
        }
        while (!stack.isEmpty()) {
            // opening parenthesis
            status[stack.removeFirst()] = '3';
        }
        int unlockCnt = 0;
        // closing parenthesis check
        for (int i = 0; i < n; i++) {
            if (status[i] == '1') continue;
            if (status[i] == '0') {
                unlockCnt++;
            } else if (status[i] == '4') {
                unlockCnt--;
            }
            if (unlockCnt < 0) return false;
        }

        unlockCnt = 0;
        // opeing parenthesis check
        for (int i = n - 1; i >= 0; i--) {
            if (status[i] == '1') continue;
            if (status[i] == '0') {
                unlockCnt++;
            } else if (status[i] == '3') {
                unlockCnt--;
            }
            if (unlockCnt < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "))()))";
        String locked = "010100";
        System.out.println("test: " + sol.canBeValid(s, locked));
    }
}