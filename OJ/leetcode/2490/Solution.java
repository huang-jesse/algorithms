class Solution {
    public boolean isCircularSentence(String sentence) {
        int n = sentence.length();
        if (n == 1) {
            return true;
        }
        char firstChar = sentence.charAt(0);
        char lastChar = sentence.charAt(n - 1);
        for (int i = 0; i < n; i++) {
            firstChar = sentence.charAt(i);
            if (lastChar != firstChar) {
                // Not circular sentence
                return false;
            }

            while (i < n && sentence.charAt(i) != ' ') {
                lastChar = sentence.charAt(i);
                i++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String sentence = "leetcode exercises sound delightful";
        // String sentence = "Leetcode is cool";
        System.out.println("test: " + sol.isCircularSentence(sentence));
    }
}