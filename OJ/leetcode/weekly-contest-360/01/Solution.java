class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int n = moves.length();
        int lCount = 0;
        int rCount = 0;
        for (int i = 0; i < n; i++) {
            if (moves.charAt(i) == 'L') {
                lCount++;
            } else if (moves.charAt(i) == 'R') {
                rCount++;
            }
        }
        return Math.abs(lCount - rCount) + n - (lCount + rCount);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String moves = "";
        System.out.println("test: " + sol.furthestDistanceFromOrigin(moves));
    }
}