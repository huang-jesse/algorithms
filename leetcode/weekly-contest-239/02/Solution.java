import java.math.BigInteger;

class Solution {
    public boolean splitString(String s) {
        BigInteger accumulate = BigInteger.ZERO;
        for (int i = 0; i < s.length()-1; i++) {
            BigInteger curNum = BigInteger.valueOf(s.charAt(i) - '0');
            accumulate = accumulate.multiply(BigInteger.TEN).add(curNum);
            if (splitString(accumulate, s, i+1))
                return true;
        }
        return false;
    }
    
    private boolean splitString(BigInteger pre, String s, int start) {
        if (start == s.length())
            return true;

        BigInteger accumulate = BigInteger.ZERO;
        for (int i = start; i < s.length(); i++) {
            BigInteger curNum = BigInteger.valueOf(s.charAt(i) - '0');
            accumulate = accumulate.multiply(BigInteger.TEN).add(curNum);
            boolean isDecrease = accumulate.equals(pre.subtract(BigInteger.ONE));
            if (isDecrease &&
                splitString(accumulate, s, i+1)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String s = "200100";
        String s = "123456789123456789";
        // String s = "10009998";
        System.out.println("test: " + sol.splitString(s));
    }
}