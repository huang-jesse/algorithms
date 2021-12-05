import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n == 0)
            return 0;
        int ans = 0;
        Set<Character> memo = new HashSet<>();
        int j = -1;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                memo.remove(s.charAt(i - 1));
            }
            while ((j+1) < n && !memo.contains(s.charAt(j+1))) {
                // 不断向右移动右指针
                memo.add(s.charAt(j+1));
                j++;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "pwwkew";
        System.out.println("test: " + sol.lengthOfLongestSubstring(s));
    }
}