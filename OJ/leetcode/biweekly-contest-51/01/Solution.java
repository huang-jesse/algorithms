class Solution {
    public String replaceDigits(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char temp;
            if (i % 2 == 1) {
                temp = shift(s.charAt(i-1), s.charAt(i) - '0');
            } else {
                temp = s.charAt(i);
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    public char shift(char a, int i) {
        return (char) ((int) a + i);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "a1c1e1";
        System.out.println("test: " + sol.replaceDigits(s));
    }
}