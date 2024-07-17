class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        int n = s.length();
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            char cur = s.charAt(0);
            if (isNumberChar(cur)) {
                return true;
            } else {
                return false;
            }
        }
        boolean existedE = false;
        boolean existedDot = false;
        boolean existedNumber = false;
        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            if (cur == '+' || cur == '-') {
                // Start of numerical
                if (i == 0 || (s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E') && i != n - 1) {
                    continue;
                } else {
                    return false;
                }
            } else if (cur == '.') {
                if (existedE || existedDot || (i == n - 1 && !isNumberChar(s.charAt(i - 1)))) {
                    return false;
                } else {
                    existedDot = true;
                    continue;
                }
            } else if (cur == 'e' || cur == 'E') {
                if (i == n - 1 || existedE || !existedNumber) {
                    return false;
                } else {
                    existedE = true;
                    continue;
                }
            } else if (isNumberChar(cur)) {
                // Number
                existedNumber = true;
            } else {
                // Illegal character
                return false;
            }
        }
        return true;
    }

    private boolean isNumberChar(char cur) {
        return '0' <= cur && cur <= '9';
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "12e+5";
        // String s = "12e+5.4";
        // String s = "-1E-16";
        // String s = "0e";
        // String s = "+-.";
        // String s = " -.";
        System.out.println("test: " + sol.isNumber(s));
    }
}