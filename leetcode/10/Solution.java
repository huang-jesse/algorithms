class Solution {
    private final static char ASTERISK = '*';
    private final static char DOT = '.';
    private int sLen;
    private int pLen;
    private char[] sArr;
    private char[] pArr;
    private Boolean[][] memo;
    public boolean isMatch(String s, String p) {
        sLen = s.length();
        pLen = p.length();
        sArr = s.toCharArray();
        pArr = p.toCharArray();
        memo = new Boolean[sLen+1][pLen];
        return dfs(0, 0);
    }

    private boolean dfs(int sIndex, int pIndex) {
        if (sIndex == sLen && pIndex == pLen) {
            return true;
        } else if (sIndex > sLen || (pIndex == pLen && sIndex < sLen)) {
            return false;
        }
        if (memo[sIndex][pIndex] != null) {
            return memo[sIndex][pIndex];
        }
        boolean ans = false;
        char pChar = pArr[pIndex];
        if (pIndex+1 < pLen && pArr[pIndex+1] == ASTERISK) {
            // next is '*'
            ans = ans || dfs(sIndex, pIndex+1);
        } else if (pChar == DOT) {
            // '.'
            ans = ans || dfs(sIndex+1, pIndex+1);
        } else if (pChar == ASTERISK) {
            // '*'
            char prePChar = pArr[pIndex-1];
            if (prePChar == DOT) {
                // pre is '.'
                boolean temp = false;
                if (pIndex+1 < pLen) {
                    // there’s more on the right side
                    for (int i = 0; i <= sLen-sIndex; i++) {
                        temp = temp || dfs(sIndex+i, pIndex+1);
                    }
                } else {
                    temp = true;
                }
                ans = ans || temp;
            } else if (prePChar == ASTERISK) {
                // pre is '*'
                ans = false;
            } else {
                // pre is letter
                int repeatCount = repeatLetterLen(sIndex, prePChar);
                boolean temp = false;
                for (int i = 0; i <= repeatCount; i++) {
                    temp = temp || dfs(sIndex+i, pIndex+1);
                }
                ans = ans || temp;
            }
        } else {
            // letter
            if (sIndex < sLen && pChar == sArr[sIndex]) {
                ans = ans || dfs(sIndex+1, pIndex+1);
            }
        }
        memo[sIndex][pIndex] = ans;
        return ans;
    }

    private int repeatLetterLen(int sIndex, char letter) {
        int count = 0;
        while (sIndex < sLen && sArr[sIndex] == letter) {
            count++;
            sIndex++;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "aabb";
        String p = "c*a*b*";
        // String s = "ab";
        // String p = ".*";
        // String s = "a";
        // String p = "ab*";
        System.out.println("test: " + sol.isMatch(s, p));
    }
}