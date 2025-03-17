class Solution {
    private final static char OPENING_BRACKET = '[';
    public int minSwaps(String s) {
        int n = s.length();
        int ans = 0;
        int openingCnt = 0;
        int totOpeningCnt = 0;
        for (int i = 0; i < n && totOpeningCnt < n / 2; i++) {
            if (s.charAt(i) == OPENING_BRACKET) {
                openingCnt++;
                totOpeningCnt++;
                continue;
            }
            // else s.charAt(i) == ']'
            if (openingCnt > 0) {
                openingCnt--;
            } else {
                // swap
                ans++;
                openingCnt++;
                totOpeningCnt++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "]]][[["; // ans = 2
        System.out.println("test: " + sol.minSwaps(s));
    }
}