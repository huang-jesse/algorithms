import java.util.HashMap;
import java.util.Map;

class Solution {
    public String oddString(String[] words) {
        Map<String, Integer> counter = new HashMap<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            int offset = word.charAt(0) - 'a';
            for (int i = 1; i < word.length(); i++) {
                sb.append(word.charAt(i) - offset);
            }
            String diffWord = sb.toString();
            counter.put(diffWord, counter.getOrDefault(diffWord, 0) + 1);
        }
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            int offset = word.charAt(0) - 'a';
            for (int i = 1; i < word.length(); i++) {
                sb.append(word.charAt(i) - offset);
            }
            String diffWord = sb.toString();
            if (counter.get(diffWord) == 1) {
                return word;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] words = {"adc","wzy","abc"};
        System.out.println("test: " + sol.oddString(words));
    }
}