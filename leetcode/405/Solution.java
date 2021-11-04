class Solution {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int digit = getHexDigit(num);
            num >>>= 4;
            sb.insert(0, getHexCharDigit(digit));
        }
        return sb.toString();
    }

    private int getHexDigit(int num) {
        return num ^ ((num >>> 4) << 4);
    }

    private char getHexCharDigit(int digit) {
        if (digit < 10) {
            return (char)('0' + digit);
        }
        return (char)('a' + digit - 10);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int num = 26;
        System.out.println("test: " + sol.toHex(num));
    }
}