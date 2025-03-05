class Solution {
    private static final char LOWERCASE_A = 'a';
    private static final char LOWERCASE_B = 'b';
    public String breakPalindrome(String palindrome) {
        int len = palindrome.length();
        if (len <= 1) return "";
        int skip = -1;
        if (len % 2 == 1) {
            skip = len / 2;
        }
        StringBuilder sb = new StringBuilder();
        boolean breaked = false;
        for (int i = 0; i < len; i++) {
            char cur = palindrome.charAt(i);
            if (breaked || i == skip) {
                sb.append(cur);
                continue;
            }
            if (cur != LOWERCASE_A) {
                sb.append(LOWERCASE_A);
                breaked = true;
                continue;
            }
            // cur == 'a';
            if (i == len - 1) {
                sb.append(LOWERCASE_B);
                breaked = true;
            } else {
                // i < len - 1
                sb.append(LOWERCASE_A);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String palindrome = "abccba"; // ans: aaccba
        // String palindrome = "aba"; // ans: abb
        System.out.println("test: " + sol.breakPalindrome(palindrome));
    }
}