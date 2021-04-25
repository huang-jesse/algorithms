import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestBeautifulSubstring(String word) {
        int max = 0;
        int fast = 1;
        int slow = 0;
        Set<Integer> set = new HashSet<>();
        if (word.charAt(0) == 'a') {
            set.add(97);// add a
        }

        int tempLen = 1;
        while (fast < word.length()) {
            if (word.charAt(slow) != 'a') {
                slow++;
                fast++;
                if (word.charAt(slow) == 'a') {
                    set.add(97);// add a
                }
            } else if (word.charAt(fast) >= word.charAt(fast-1)) {
                set.add((int)word.charAt(fast));
                fast++;
                tempLen++;
            } else {
                if (set.size() == 5) {
                    max = Math.max(max, tempLen);
                }
                tempLen = 1;
                set.clear();
                slow = fast;
                fast++;
            }
        }
        if (set.size() == 5) {
            max = Math.max(max, tempLen);
        }

        return max;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String word = "a";
        System.out.println("longestBeautifulSubstring: "+ sol.longestBeautifulSubstring(word));
    }
}