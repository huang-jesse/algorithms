import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean equalFrequency(String word) {
        int[] counter = new int[26];
        int n = word.length();
        for (int i = 0; i < n; i++) {
            counter[word.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (counter[i] == 0) {
                continue;
            }
            counter[i]--;
            // check
            Set<Integer> set = new HashSet<>();
            for (int count : counter) {
                set.add(count);
            }
            if (set.size() <= 2) {
                // contains only 0 and one of others
                return true;
            }
            counter[i]++;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String word = "abc";
        System.out.println("test: " + sol.equalFrequency(word));
    }
}