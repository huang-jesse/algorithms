class Solution {
    public int maxProduct(String[] words) {
        int n = words.length;
        int ans = 0;
        int[] masks = new int[n];
        for (int i = 0; i < n; i++) {
            masks[i] = StringEncodingByBit(words[i]);
        }

        for (int i = 0; i < n; i++) {
            int curMask = masks[i];
            String curWord = words[i];
            for (int j = i+1; j < n; j++) {
                int nextMask = masks[j];
                if ((curMask & nextMask) == 0) {
                    String nextWord = words[j];
                    ans = Math.max(ans, curWord.length() * nextWord.length());
                }
            }
        }
        return ans;
    }

    private static int StringEncodingByBit(String word) {
        int encoder = 0;
        int m = word.length();
        for (int i = 0; i < m; i++) {
            encoder |= 1 << word.charAt(i) - 'a';
        }
        return encoder;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] words = {"abcw","baz","foo","bar","xtfn","abcdef"};
        // String[] words = {"a","aa","aaa","aaaa"};
        System.out.println("test: " + sol.maxProduct(words));
    }
}