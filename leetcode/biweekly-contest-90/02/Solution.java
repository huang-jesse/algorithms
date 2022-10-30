import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> ans = new ArrayList<>();
        int n = queries[0].length();
        for (String query : queries) {
            for (String word : dictionary) {
                int diffCount = 0;
                for (int i = 0; i < n; i++) {
                    if (word.charAt(i) != query.charAt(i)) {
                        diffCount++;
                    }
                }
                if (diffCount <= 2) {
                    ans.add(query);
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] queries = {"word","note","ants","wood"};
        String[] dictionary = {"wood","joke","moat"};
        System.out.println("test: " + sol.twoEditWords(queries, dictionary));
    }
}