import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length())
            return false;
        int n = s.length();
        char[] sChars = s.toCharArray();
        Map<Character, Integer> charCounter = new HashMap<>();
        List<Character> misplace = new ArrayList<>();
        List<Character> goalChars = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char sChar = sChars[i];
            char goalChar = goal.charAt(i);
            if (sChar != goalChar) {
                misplace.add(sChar);
                goalChars.add(goalChar);
            }
            if (misplace.size() > 2) {
                return false;
            }
            charCounter.put(goalChar, charCounter.getOrDefault(goalChar, 0) + 1);
        }
        if (misplace.isEmpty()) {
            // s and goal is same string
            for (int counter : charCounter.values()) {
                // check if has same char in goal
                if (counter >= 2) {
                    return true;
                }
            }
        } else if (misplace.size() == 2) {
            // s has two misplace chars
            if (misplace.get(0).equals(goalChars.get(1))
                && misplace.get(1).equals(goalChars.get(0))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String s = "ab";
        // String goal = "ba";
        String s = "abcaa";
        String goal = "abcbb";
        System.out.println("test: " + sol.buddyStrings(s, goal));
    }
}