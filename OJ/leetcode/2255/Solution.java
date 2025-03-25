class Solution {
    public int countPrefixes(String[] words, String s) {
        int ans = 0;
        for (String word : words) {
            if (s.startsWith(word)) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] words = {"a","b","c","ab","bc","abc"};
        String s = "abc";
        System.out.println("test: " + sol.countPrefixes(words, s));
    }
}