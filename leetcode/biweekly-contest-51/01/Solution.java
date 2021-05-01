class Solution {
    public String replaceDigits(String s) {
        char[] arr = new char[s.length()];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 1) {
                arr[i] = shift(arr[i-1], Integer.valueOf(String.valueOf(s.charAt(i))));
            } else {
                arr[i] = s.charAt(i);
            }
            sb.append(arr[i]);
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