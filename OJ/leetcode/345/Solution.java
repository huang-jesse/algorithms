import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    private Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int start = 0;
        int end = n-1;
        while (start < end) {
            while (start < n && !isVowel(chars[start])) {
                start++;
            }
            while (end > 0 && !isVowel(chars[end])) {
                end--;
            }

            if (start < end) {
                swap(chars, start, end);
                start++;
                end--;
            }
        }
        return String.valueOf(chars);
    }

    private boolean isVowel(char cur) {
        return vowels.contains(cur);
    }

    private void swap(char[] chars, int start, int end) {
        char temp = chars[start];
        chars[start] = chars[end];
        chars[end] = temp;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "hello";
        System.out.println("test: " + sol.reverseVowels(s));
    }
}