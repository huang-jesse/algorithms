class Solution {
    private final static int MOD = 26;
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] diff = new int[n+1];
        for (int[] shift : shifts) {
            int start = shift[0];
            int end = shift[1];
            int direction = shift[2] == 1 ? 1 : -1;
            diff[start] = (diff[start] + direction + MOD) % MOD;
            diff[end + 1] = (diff[end + 1] - direction + MOD) % MOD;
        }
        int[] preDiff = new int[n];
        preDiff[0] = diff[0];
        for (int i = 1; i < n; i++) {
            preDiff[i] = (diff[i] + preDiff[i - 1]) % MOD;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char cur = chars[i];
            int curNum = cur - 'a';
            int shiftNum = (curNum + preDiff[i]) % MOD;
            char shiftChar = (char)('a' + shiftNum);
            sb.append(shiftChar);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "";
        int[][] shifts = {{}};
        System.out.println("test: " + sol.shiftingLetters(s, shifts));
    }
}