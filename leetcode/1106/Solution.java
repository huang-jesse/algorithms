import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public boolean parseBoolExpr(String expression) {
        Deque<Character> nums = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();
        int n = expression.length();
        for (int i = 0; i < n; i++) {
            char cur = expression.charAt(i);
            if (cur == ',') {
                continue;
            }
            if (cur == '(') {
                nums.push('-');
            } else if (cur == '!' || cur == '|' || cur == '&') {
                ops.push(cur);
            } else if (cur == 't' || cur == 'f') {
                nums.push(cur);
            } else if (cur == ')') {
                // cur == ')'
                char curNum = ' ';
                char curOp = ops.pop();
                while (!nums.isEmpty() && nums.peek() != '-') {
                    char preNum = nums.pop();
                    curNum = curNum == ' ' ? preNum : calc(curNum, preNum, curOp);
                }
                if (curOp == '!') {
                    curNum = curNum == 't' ? 'f' : 't';
                }
                nums.pop();
                nums.push(curNum);
            }
        }
        return nums.pop() == 't';
    }

    private char calc(char a, char b, char op) {
        boolean x = a == 't';
        boolean y = b == 't';
        boolean res;
        if (op == '|') {
            res = x | y;
        } else {
            res = x & y;
        }
        return res ? 't' : 'f';
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String expressiong = "!(&(f,t))";
        String expressiong = "!(&(&(f),&(!(t),&(f),|(f)),&(!(&(f)),&(t),|(f,f,t))))";
        System.out.println("test: " + sol.parseBoolExpr(expressiong));
    }
}