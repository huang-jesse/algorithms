import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> stringMatching(String[] words) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        if (n == 1) {
            return ans;
        }
        for (int i = 0; i < n; i++) {
            String curWord = words[i];
            int curLen = curWord.length();
            for (int j = 0; j < n; j++) {
                String otherWord = words[j];
                int otherLen = otherWord.length();
                if (curLen >= otherLen) {
                    continue;
                }
                if (otherWord.indexOf(curWord) > -1) {
                    ans.add(curWord);
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] words = {"mass","as","hero","superhero"};
        System.out.println("test: " + sol.stringMatching(words));
    }
}