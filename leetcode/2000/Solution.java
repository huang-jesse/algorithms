class Solution {
    public String reversePrefix(String word, char ch) {
        int index = -1;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            if (word.charAt(i) == ch) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            StringBuilder sb = new StringBuilder();
            for (int i = index; i >= 0; i--) {
                sb.append(word.charAt(i));
            }
            for (int i = index+1; i < n; i++) {
                sb.append(word.charAt(i));
            }
            return sb.toString();
        } else {
            return word;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String word = "abcdefd";
        char ch = 'd';
        System.out.println("test: " + sol.reversePrefix(word, ch));
    }
}