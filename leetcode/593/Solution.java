class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[] first = p1;
        // diagonally of first
        int[] second = p2;
        int[] third = p3;
        int[] fourth = p4;
        int ds2 = getSquareDs(p1, p2);
        int ds3 = getSquareDs(p1, p3);
        if (ds2 == ds3) {
            second = p4;
            third = p2;
            fourth = p3;
        } else if (ds2 < ds3) {
            second = p3;
            third = p2;
        }

        int catercorner1 = getSquareDs(first, second);
        int catercorner2 = getSquareDs(third, fourth);
        if (catercorner1 != catercorner2) {
            return false;
        }
        int sideLength1 = getSquareDs(first, third);
        int sideLength2 = getSquareDs(first, fourth);
        int sideLength3 = getSquareDs(second, third);
        int sideLength4 = getSquareDs(second, fourth);
        if (sideLength1 == 0 || sideLength2 == 0 || sideLength3 == 0 || sideLength4 == 0
            || sideLength1 != sideLength2 || sideLength1 != sideLength3 || sideLength1 != sideLength4) {
            return false;
        }
        return true;
    }

    private static int getSquareDs(int[] point1, int[] point2) {
        return (point1[0]-point2[0]) * (point1[0]-point2[0]) + (point1[1]-point2[1]) * (point1[1]-point2[1]);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] p1 = {1,2};
        // int[] p2 = {3,4};
        // int[] p3 = {3,0};
        // int[] p4 = {5,2};
        int[] p1 = {0,0};
        int[] p2 = {1,1};
        int[] p3 = {1,0};
        int[] p4 = {0,1};
        System.out.println("test: " + sol.validSquare(p1, p2, p3, p4));
    }
}