class Solution {
    public int atMostNGivenDigitSet(String[] digits, int n) {
        char[] numDigits = String.valueOf(n).toCharArray();
        int m = numDigits.length;
        int k = digits.length;
        int ans = 0;
        for (int digitSize = 1; digitSize < m; digitSize++) {
            ans += Math.pow(k, digitSize);
        }
        for (int i = 0; i < m; i++) {
            char currentDigit = numDigits[i];
            int ceilIndex = binarySearchLeftBoundary(digits, currentDigit);
            if (ceilIndex == -1) {
                break;
            }
            if (digits[ceilIndex].charAt(0) == currentDigit) {
                ans += ceilIndex * Math.pow(k, (m - i - 1));
                if (i == m - 1) {
                    ans++;
                }
            } else {
                // digits[ceilIndex].charAt(0) < currentDigit
                ans += (ceilIndex + 1) * Math.pow(k, (m - i - 1));
                break;
            }
        }
        return ans;
    }

    private int binarySearchLeftBoundary(String[] digits, char target) {
        if (digits[0].charAt(0) > target) {
            return -1;
        }
        int l = 0;
        int r = digits.length - 1;
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            if (digits[mid].charAt(0) <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String[] digits = {"1","3","5","7"};
        // int n = 100; // ans = 20
        String[] digits = {"3","4","8"};
        int n = 4; // ans = 2
        // String[] digits = {"1","4","9"};
        // int n = 1000000000; // ans = 29523
        System.out.println("test: " + sol.atMostNGivenDigitSet(digits, n));
    }
}