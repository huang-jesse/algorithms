class Solution {
    public int minSteps(String s, String t) {
        int[] scounter = new int[26];
        int m = s.length();
        int[] tcounter = new int[26];
        int n = t.length();
        for (int i = 0; i < m; i++) {
            scounter[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < n; i++) {
            tcounter[t.charAt(i)-'a']++;
        }
        int ans = 0;
        // count
        for (int i = 0; i < 26; i++) {
            ans += Math.max(0, scounter[i] - tcounter[i]);
            ans += Math.max(0, tcounter[i] - scounter[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "night";
        String t = "thing";
        // String s = "leetcode";
        // String t = "coats";
        System.out.println("test: " + sol.minSteps(s, t));
    }
}