class Solution {
    public String maximumOddBinaryNumber(String s) {
        char[] chars = s.toCharArray();
        int countOneBit = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                countOneBit++;
            }
        }
        StringBuilder sb = new StringBuilder();
        countOneBit--;
        while (countOneBit > 0) {
            sb.append('1');
            countOneBit--;
        }
        for (int i = sb.length(); i < chars.length - 1; i++) {
            sb.append('0');
        }
        sb.append('1');
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "0101";
        System.out.println("test: " + sol.maximumOddBinaryNumber(s));
    }
}