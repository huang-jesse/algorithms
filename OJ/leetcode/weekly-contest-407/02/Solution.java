import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public boolean doesAliceWin(String s) {
        int n = s.length();
        Set<Character> charSet = Arrays.asList('a', 'e', 'i', 'o', 'u').stream().collect(Collectors.toSet());
        int vowelCount = 0;
        for (int i = 0; i < n; i++) {
            if (charSet.contains(s.charAt(i))) vowelCount++;
        }
        return vowelCount > 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String s = "leetcoder";
        String s = "bbc";
        System.out.println("test: " + sol.doesAliceWin(s));
    }
}