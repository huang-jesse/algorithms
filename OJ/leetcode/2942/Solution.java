import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        int n = words.length;
        String searchStr = "" + x;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (words[i].contains(searchStr)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] words = {"leet","code"};
        char x = 'e';
        System.out.println("test: " + sol.findWordsContaining(words, x));
    }
}