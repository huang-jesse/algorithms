import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    private static final String[] LETTERS_ARR = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private List<String> ans = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        if (n == 0) {
            return Collections.emptyList();
        }
        backtrack4AddLetterComb(digits, 0, new StringBuilder());
        return ans;
    }

    private void backtrack4AddLetterComb(String digits, int index, StringBuilder letterCombSb) {
        if (index >= digits.length()) {
            ans.add(letterCombSb.toString());
            return;
        }
        int letterIndex = (digits.charAt(index) - '0') - 2;
        String letters = LETTERS_ARR[letterIndex];
        for (int i = 0; i < letters.length(); i++) {
            char letter = letters.charAt(i);
            // add leeter to last
            letterCombSb.append(letter);
            backtrack4AddLetterComb(digits, index+1, letterCombSb);
            // remove last letter
            letterCombSb.deleteCharAt(index);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String digits = "23";
        System.out.println("test: " + sol.letterCombinations(digits));
    }
}