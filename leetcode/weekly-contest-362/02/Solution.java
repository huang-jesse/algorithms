class Solution {
    public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        int xDistance = Math.abs(sx - fx);
        int yDistance = Math.abs(sy - fy);
        int max = Math.max(xDistance, yDistance);
        if (max == 0 && t == 1) {
            return false;
        } else {
            return max <= t;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int sx = 2;
        // int sy = 4;
        // int fx = 7;
        // int fy = 7;
        // int t = 6;
        int sx = 1;
        int sy = 2;
        int fx = 1;
        int fy = 2;
        int t = 1;
        System.out.println("test: " + sol.isReachableAtTime(sx, sy, fx, fy, t));
    }
}