import java.util.Arrays;
import java.util.List;

class Solution {
    public boolean isAcronym(List<String> words, String s) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word.charAt(0));
        }
        return s.equals(sb.toString());
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<String> words = Arrays.asList("");
        String s = "";
        System.out.println("test: " + sol.isAcronym(words, s));
    }
}