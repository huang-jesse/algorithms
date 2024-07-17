class Solution {
    public int prefixCount(String[] words, String pref) {
        int ans = 0;
        int len = pref.length();
        for (String word : words) {
            if (word.length() < len) {
                continue;
            }
            String sub = word.substring(0, len);
            if (sub.equals(pref)) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] words = {"pay","attention","practice","attend"};
        String pref = "at";
        System.out.println("test: " + sol.prefixCount(words, pref));
    }
}