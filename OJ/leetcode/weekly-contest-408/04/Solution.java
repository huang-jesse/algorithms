class Solution {
    public boolean canReachCorner(int X, int Y, int[][] circles) {

        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int x = 3;
        // int y = 4;
        // int[][] circles = {{2,1,1}}; // true
        int x = 3;
        int y = 3;
        int[][] circles = {{2,1,1},{1,2,1}}; // false
        System.out.println("test: " + sol.canReachCorner(x, y, circles));
    }
}