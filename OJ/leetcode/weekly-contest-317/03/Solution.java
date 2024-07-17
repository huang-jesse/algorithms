import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public long makeIntegerBeautiful(long n, int target) {
        int digitSize = 0;
        List<Integer> digits = new ArrayList<>();
        long tempNum = n;
        while (tempNum > 0) {
            digits.add((int)(tempNum % 10));
            tempNum /= 10;
            digitSize++;
        }
        Collections.reverse(digits);
        long num = 0;
        for (int i = 0; i < digitSize; i++) {
            int curDigit = digits.get(i);
            long pow = (long)Math.pow(10, digitSize - i - 1);
            if (target > curDigit) {
                target -= curDigit;
                num = num * 10 + curDigit;
            } else if (target == curDigit && n % pow == 0) {
                num = (num * 10 + curDigit) * pow;
                break;
            } else {
                // target == curDigit && n % pow != 0 or target < curDigit
                num = (num + 1) * 10 * pow;
                break;
            }
        }
        return num - n;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        long n = 460;
        int target = 5;
        // long n = 467;
        // int target = 6;
        System.out.println("test: " + sol.makeIntegerBeautiful(n, target));
    }
}