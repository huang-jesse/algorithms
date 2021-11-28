import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int m = s.length();
        int n = p.length();
        List<Integer> ans = new ArrayList<>();
        if (n > m)
            return ans;
        int[] pCounter = new int[26];
        for (int i = 0; i < n; i++) {
            char letter = p.charAt(i);
            pCounter[letter - 'a']++;
        }
        int j = 0;
        int[] subCounter = new int[26];
        for (int i = 0; i <= (m-n); i++) {
            while (j < (i+n)) {
                char letter = s.charAt(j);
                subCounter[letter - 'a']++;
                j++;
            }
            if (isAnagram(pCounter, subCounter)) {
                ans.add(i);
            }
            char curLetter = s.charAt(i);
            subCounter[curLetter - 'a']--;
        }
        return ans;
    }

    private static boolean isAnagram(int[] pCounter, int[] subCounter) {
        for (int i = 0; i < 26; i++) {
            if (pCounter[i] != subCounter[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "abcad";
        String p = "bca";
        System.out.println("test: " + sol.findAnagrams(s, p));
    }
}