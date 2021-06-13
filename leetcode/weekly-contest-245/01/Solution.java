import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean makeEqual(String[] words) {
        int lenOfWords = words.length;
        if (lenOfWords == 1) return true;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (String s : words) {
            char[] chars = s.toCharArray();
            for (char cur : chars) {
                countMap.put((int)cur, countMap.getOrDefault((int)cur, 0) + 1);
            }
        }
        for (Integer count : countMap.values()) {
            if (count % lenOfWords != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] words = {"caaaaa","aaaaaaaaa","a","bbb","bbbbbbbbb","bbb","cc","cccccccccccc","ccccccc","ccccccc","cc","cccc","c","cccccccc","c"};
        System.out.println("test: " + sol.makeEqual(words));
    }
}