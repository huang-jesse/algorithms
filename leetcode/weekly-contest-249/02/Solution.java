import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    Map<String, Boolean> visited = new HashMap<>();
    Map<String, Integer> memo = new HashMap<>();
    public int countPalindromicSubsequence(String s) {
        char[] chars = s.toCharArray();
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            char cur = (char) (97 + i);
            ans += countPalindromic(cur, chars);
        }
        return ans;
    }

    private static int countPalindromic(char letter, char[] chars) {
        int startIndex = getStartIndex(letter, chars);
        int endIndex = getEndIndex(letter, chars);
        int countDiffLetters = countDiffLetters(chars, startIndex, endIndex);
        return countDiffLetters;
    }

    private static int countDiffLetters(char[] chars, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start+1; i < end; i++) {
            if (!set.contains(chars[i])) {
                set.add(chars[i]);
            }
        }
        return set.size();
    }

    private static int getStartIndex(char letter, char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == letter)
                return i;
        }
        return -1;
    }
    private static int getEndIndex(char letter, char[] chars) {
        for (int i = chars.length-1; i >= 0; i--) {
            if (chars[i] == letter)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String s = "uuuuu";
        String s = "aabca";
        System.out.println("test: " + sol.countPalindromicSubsequence(s));
    }
}