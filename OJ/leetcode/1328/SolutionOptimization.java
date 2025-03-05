class SolutionOptimization {
    private static final char LOWERCASE_A = 'a';
    private static final char LOWERCASE_B = 'b';
    public String breakPalindrome(String palindrome) {
        int len = palindrome.length();
        if (len <= 1) return "";
        char[] s = palindrome.toCharArray();
        for (int i = 0; i < len / 2; i++) {
            if (s[i] != LOWERCASE_A) {
                s[i] = LOWERCASE_A;
                return new String(s);
            }
        }
        s[len - 1] = LOWERCASE_B;
        return new String(s);
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        String palindrome = "abccba"; // ans: aaccba
        // String palindrome = "aba"; // ans: abb
        System.out.println("test: " + sol.breakPalindrome(palindrome));
    }
}