import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    private final Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
    public boolean halvesAreAlike(String s) {
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            if (!vowels.contains(cur)) {
                continue;
            }
            if (i < n / 2) {
                count++;
            } else {
                count--;
            }
        }
        return count == 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "textbook";
        // String s = "book";
        System.out.println("test: " + sol.halvesAreAlike(s));
    }
}