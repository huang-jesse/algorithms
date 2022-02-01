class Solution {
    public String longestNiceSubstring(String s) {
        int maxlen = 0;
        int[] ansIndexs = new int[2];
        ansIndexs[1] = -1;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            boolean[] lowercaseExisted = new boolean[26];
            boolean[] uppercaseExisted = new boolean[26];
            for (int j = i; j < n; j++) {
                char cur = s.charAt(j);
                if (cur >= 97) {
                    // lowercase
                    lowercaseExisted[s.charAt(j) - 'a'] = true;
                } else {
                    // uppercase
                    uppercaseExisted[s.charAt(j) - 'A'] = true;
                }
                if (isNiceSubString(lowercaseExisted, uppercaseExisted)) {
                    int len = j - i + 1;
                    if (len > maxlen) {
                        maxlen = len;
                        ansIndexs[0] = i;
                        ansIndexs[1] = j;
                    }
                }
            }
        }
        return s.substring(ansIndexs[0], ansIndexs[1]+1);
    }

    private boolean isNiceSubString(boolean[] lowercaseExisted, boolean[] uppercaseExisted) {
        for (int i = 0; i < 26; i++) {
            if (lowercaseExisted[i] ^ uppercaseExisted[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "dDzeE";
        System.out.println("test: " + sol.longestNiceSubstring(s));
    }
}