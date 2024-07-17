class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // bottomLeft x1,y1
        // topRight x2,y2
        // topLeft
        int x3 = x1;
        int y3 = y2;
        // bottomRight
        int x4 = x2;
        int y4 = y1;
        // Classfied discussion that the position of the center of circle relative to the square
        // 1. the center of circle in square
        if (x1 <= xCenter && xCenter <= x2
            && y1 <= yCenter && yCenter <= y2) {
            return true;
        }
        int radiusSqr = radius * radius;
        int x = x1;
        int y = y1;
        // 2. the center of circle in topLeft area
        if (xCenter < x3 && yCenter > y3) {
            x = x3;
            y = y3;
        } else if (yCenter > y3 && x3 <= xCenter && xCenter <= x2) {
            // 3. the center of circle in top area
            // Calculate the vertical distance
            x = xCenter;
            y = y3;
        } else if (xCenter > x2 && yCenter > y2) {
            // 4. the center of circle in topRight area
            x = x2;
            y = y2;
        } else if (xCenter > x2 && y4 <= yCenter && yCenter <= y2) {
            // 5. the center of circle in right area
            // Calculate the horizontal distance
            x = x2;
            y = yCenter;
        } else if (xCenter >= x4 && yCenter <= y4) {
            // 6. the center of circle in bottomRight area
            x = x4;
            y = y4;
        } else if (yCenter < y1 && x1 <= xCenter && xCenter <= x4) {
            // 7. the center of circle in bottom area
            x = xCenter;
            y = y1;
        } else if (xCenter <= x1 && yCenter <= y1) {
            // 8. the center of circle in bottomLeft area
            x = x1;
            y = y1;
        } else {
            // 9. the center of circle in left area
            x = x1;
            y = yCenter;
        }
        int xDiff = xCenter - x;
        int yDiff = yCenter - y;
        if (xDiff * xDiff + yDiff * yDiff <= radiusSqr) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int radius = 1;
        int xCenter = 0;
        int yCenter = 0;
        int x1 = 1;
        int y1 = -1;
        int x2 = 3;
        int y2 = 1;
        System.out.println("test: " + sol.checkOverlap(radius, xCenter, yCenter, x1, y1, x2, y2));
    }
}