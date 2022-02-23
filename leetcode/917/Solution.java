class Solution {
    public String reverseOnlyLetters(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int i = 0;
        int j = n-1;
        while (i < j) {
            while (j < j && !Character.isLetter(chars[i])) {
                i++;
            }
            while (i < j && !Character.isLetter(chars[j])) {
                j--;
            }
            if (i < j) {
                // swap
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            }
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "Test1ng-Leet=code-Q!";
        // Qedo1ct-eeLg=ntse-T!
        System.out.println("test: " + sol.reverseOnlyLetters(s));
    }
}