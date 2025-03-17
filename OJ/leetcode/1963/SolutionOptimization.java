class SolutionOptimization {
    private final static char OPENING_BRACKET = '[';
    public int minSwaps(String s) {
        int n = s.length();
        int ans = 0;
        int openingCnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == OPENING_BRACKET) {
                openingCnt++;
            } else if (openingCnt > 0) {
                // ']'
                openingCnt--;
            } else {
                //  ']'
                // swap
                ans++;
                openingCnt++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        String s = "]]][[["; // ans = 2
        System.out.println("test: " + sol.minSwaps(s));
    }
}