class Solution {
    public int myAtoi(String s) {
        char[] c = s.trim().toCharArray();
        if (c.length == 0) return 0;
        int i = 1, sign = 1;
        if (c[0] == '-') sign = -1;
        else if (c[0] != '+') i = 0;
        int res = 0;
        int temp = Integer.MAX_VALUE / 10;
        for (int j = i; j < c.length; j++) {
            if (c[j] < '0' || c[j] > '9') break;
            if (res > temp || res == temp && c[j] > '7') return sign == 1? Integer.MAX_VALUE: Integer.MIN_VALUE;
            res = res * 10 + c[j] - '0';
        }
        return res * sign;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String s = "   -42";
        String s = "18446744073709551617";
        System.out.println("test: " + sol.myAtoi(s));
    }
}