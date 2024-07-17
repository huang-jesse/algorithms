class Solution {
    private static final int INF = 0x3f3f3f3f;
    public int maxNumberOfBalloons(String text) {
        int[] balonCounter = new int[5];
        int n = text.length();
        for (int i = 0; i < n; i++) {
            char letter = text.charAt(i);
            if (letter == 'b') {
                balonCounter[0]++;
            } else if (letter == 'a') {
                balonCounter[1]++;
            } else if (letter == 'l') {
                balonCounter[2]++;
            } else if (letter == 'o') {
                balonCounter[3]++;
            } else if (letter == 'n') {
                balonCounter[4]++;
            }
        }
        int ans = INF;
        for (int i = 0; i < 5; i++) {
            if (i == 2 || i == 3) {
                ans = Math.min(ans, balonCounter[i] / 2);
            } else {
                ans = Math.min(ans, balonCounter[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String text = "loonbalxballpoon";
        System.out.println("test: " + sol.maxNumberOfBalloons(text));
    }
}