class Solution {
    private final static char A = 'a';
    private final static char E = 'e';
    private final static char I = 'i';
    private final static char O = 'o';
    private final static char U = 'u';
    public int countOfSubstrings(String word, int k) {
        int n = word.length();
        int ans = 0;
        // aeiou mask
        int vowelMask = (1 << (A - A)) | (1 << (E - A)) | (1 << (I - A)) | (1 << (O - A)) | (1 << (U - A));
        for (int l = 0; l < n - 5 - k + 1; l++) {
            int cntConsonants = 0;
            int curVowel = 0;
            for (int r = l; r < n; r++) {
                int cur = word.charAt(r) - A;
                if (((vowelMask >> cur) & 1) == 1) {
                    // vowel
                    curVowel |= (1 << cur);
                } else {
                    cntConsonants++;
                }
                if (cntConsonants > k) break;
                if (curVowel == vowelMask && cntConsonants == k) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String word = "ieaouqqieaouqq";
        int k = 1;
        System.out.println("test: " + sol.countOfSubstrings(word, k));
    }
}