class Solution {
    public int orchestraLayout(int num, int xPos, int yPos) {
        int yMin = 0;
        int yMax = num-1;
        int xMin = 0;
        int xMax = num-1;
        int y = 0;
        int x = 0;
        int cur = 1;

        while (true) {
            if (x == xPos && y == yPos) return cur;
            if (x == xMin && y < yMax) {
                // top row ->
                // when is not first row
                if (xMin > yMin) {
                    yMin++;
                }
                y++;
            } else if (y == yMax && x < xMax) {
                // right column
                if (yMin == xMin) {
                    xMin++;
                }
                x++;
            } else if (x == xMax && y > yMin) {
                // bottom row <-
                if (xMax == yMax) {
                    yMax--;
                }
                y--;
            } else if (y == yMin && x > xMin) {
                // left column
                if (yMax < xMax) {
                    xMax--;
                }
                x--;
            }
            cur++;
            if (cur != 9) cur = cur % 9;
        }
    }

    public static void main(String[] args) {

        int num = 3;
        int xPos = 0;
        int yPos = 2;
        Solution sol = new Solution();
        System.out.println("Current index: " + sol.orchestraLayout(num, xPos, yPos));
    }
}