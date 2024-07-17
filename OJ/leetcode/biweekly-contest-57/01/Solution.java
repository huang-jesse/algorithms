import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean areOccurrencesEqual(String s) {
        int n = s.length();
        if (n == 1)
            return true;
        Map<Character, Integer> charCount = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char cur : chars) {
            int curCount = charCount.getOrDefault(cur, 0);
            charCount.put(cur, curCount+1);
        }
        long count = charCount.values().stream().distinct().count();
        return count == 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "aaabb";
        System.out.println("test: " + sol.areOccurrencesEqual(s));
    }
}