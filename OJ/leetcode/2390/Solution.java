class Solution {
    private static final char STAR_CHAR = '*';
    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int starCount = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == STAR_CHAR) {
                starCount++;
            } else {
                if (starCount == 0) {
                    sb.append(s.charAt(i));
                } else {
                    starCount--;
                }
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "leet**cod*e";
        System.out.println("test: " + sol.removeStars(s));
    }
}