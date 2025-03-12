class SolutionSlidewindow {
    private final static char A = 'a';
    private final static char E = 'e';
    private final static char I = 'i';
    private final static char O = 'o';
    private final static char U = 'u';
    public int countOfSubstrings(String word, int k) {
        // 常见转换：“恰好包含 k 个的答案”，变为“至少包含 k 个的答案，减去至少包含 (k+1) 个的答案”。
        return count(word, k) - count(word, k + 1);
    }

    /**
     * 每个元音字母至少出现一次，并且至少包含 k 个辅音字母的子串个数
     * @param word
     * @param k
     * @return
     */
    private int count(String word, int k) {
        int n = word.length();
        int ans = 0;
        // aeiou mask
        int vowelMask = (1 << (A - A)) | (1 << (E - A)) | (1 << (I - A)) | (1 << (O - A)) | (1 << (U - A));
        int cntConsonants = 0;
        int[] counter = new int[26];
        int curVowel = 0;
        for (int l = 0, r = 0; r < n; r++) {
            int cur = word.charAt(r) - A;
            if (((vowelMask >> cur) & 1) == 1) {
                // vowel
                curVowel |= (1 << cur);
                counter[cur]++;
            } else {
                cntConsonants++;
            }

            // slide window
            while (curVowel == vowelMask && cntConsonants >= k) {
                int left = word.charAt(l) - A;
                if (((vowelMask >> left) & 1) == 1) {
                    // vowel
                    counter[left]--;
                    if (counter[left] == 0) {
                        curVowel ^= (1 << left);
                    }
                } else {
                    // consonant
                    cntConsonants--;
                }
                l++;
            }
            // 起始下标为 [0..(l - 1)] 的范围都是满足 “每个元音字母至少出现一次，并且至少包含 k 个辅音字母的子串个数” 的答案。
            ans += l;
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionSlidewindow sol = new SolutionSlidewindow();
        String word = "ieaouqqieaouqq";
        int k = 1;
        System.out.println("test: " + sol.countOfSubstrings(word, k));
    }
}