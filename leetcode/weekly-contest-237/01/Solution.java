import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean checkIfPangram(String sentence) {
        if (sentence.length() < 26) return false;
        Set<String> set = new HashSet<>();
        for(int i = 0; i < sentence.length(); i++) {
            String s = String.valueOf(sentence.charAt(i));
            set.add(s);
            if (set.size() >= 26) return true;
        }
        return false;
    }

    public static void main(String[] args) {

        // String sentence = "leetcode";
        String sentence = "thequickbrownfoxjumpsoverthelazydog";
        Solution sol = new Solution();
        System.out.println("Ok: "+ sol.checkIfPangram(sentence));
    }
}