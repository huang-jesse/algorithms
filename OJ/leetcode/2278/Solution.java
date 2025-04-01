class Solution {
    public int percentageLetter(String s, char letter) {
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == letter) {
                cnt++;
            }
        }
        return cnt * 100 / n;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "foobar";
        char letter = 'o';
        System.out.println("test: " + sol.percentageLetter(s, letter));
    }
}