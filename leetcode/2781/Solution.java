import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public int longestValidSubstring(String word, List<String> forbidden) {
        Set<String> forbiddenSet = forbidden.stream().collect(Collectors.toSet());
        int n = word.length();
        int left = n - 1;
        int right = n - 1;
        int ans = 0;
        while (left >= 0) {
            int rightBoundary = Math.min(left + 9, right);
            if (isValid(word, forbiddenSet, left, rightBoundary)) {
                ans = Math.max(ans, right - left + 1);
                left--;
            } else {
                // Forbidden
                right = Math.min(left + 9 - 1, right - 1);
                if (left > right) {
                    left = right;
                }
            }
        }
        return ans;
    }

    private boolean isValid(String word, Set<String> forbiddenSet, int start, int end) {
        for (int i = end; i >= start; i--) {
            if (forbiddenSet.contains(word.substring(start, i + 1))) {
                // Forbidden
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String word = "cbaaaabc";
        List<String> forbidden = Arrays.asList("aaa","cb");
        System.out.println("test: " + sol.longestValidSubstring(word, forbidden));
    }
}