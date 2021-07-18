import java.util.HashSet;
import java.util.Set;

class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        String[] words = text.split(" ");
        Set<Character> brokenSet = new HashSet<>();
        for (Character cur : brokenLetters.toCharArray()) {
            brokenSet.add(cur);
        }

        int ans = 0;
        for (String word : words) {
            boolean isOk = true;
            for (char letter : word.toCharArray()) {
                if (brokenSet.contains(letter)) {
                    isOk = false;
                    break;
                }
            }
            if (isOk)
                ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String text = "leet code";
        String brokenLeString = "e";
        System.out.println("test: " + sol.canBeTypedWords(text, brokenLeString));
    }
}