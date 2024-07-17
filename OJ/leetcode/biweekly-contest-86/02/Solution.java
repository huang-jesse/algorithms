class Solution {
    public boolean isStrictlyPalindromic(int n) {
        for (int i = n - 2; i >= 2; i--) {
            String digits = getDigits(n, i);
            if (!check(digits)) {
                return false;
            }
        }
        return true;
    }

    private String getDigits(int n, int base) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int remain = n % base;
            n /= base;
            sb.append(String.valueOf(remain));
        }
        return sb.toString();
    }

    private boolean check(String digits) {
        int n = digits.length();
        for (int i = 0; i < n / 2; i++) {
            if (digits.charAt(i) != digits.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int n = 9;
        // int n = 1;
        int n = 100000;
        System.out.println("test: " + sol.isStrictlyPalindromic(n));
    }
}