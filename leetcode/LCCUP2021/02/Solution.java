class Solution {
    public int orchestraLayout(int num, int xPos, int yPos) {
        // layer of current position
        long t = Math.min(Math.min(xPos, num-1-xPos), Math.min(yPos, num-1-yPos));
        // total num of 0 to t-1 layer(not include t layer) size
        // sum of arithmetic sequence (like a1*n + n*(n-1)/2*n)
        long total = ((long)4*t*(num-t)) % 9;
        // edge lenth of t layer
        long l = num + ((t+1)-1)*(-2);

        long offset = 0;
        // four cases for calculate the num of current layer size
        if (xPos == t) {
            // (xPos,yPos) on the top side
            offset = ((yPos - t) + 1) % 9;
        } else if (yPos == num-t-1) {
            // (xPos,yPos) on the right side
            offset = (l + (xPos - t)) % 9;
        } else if (xPos == num-t-1) {
            // (xPos,yPos) on the bottom side
            offset = (2*l-1 + (l - (yPos-t) - 1)) % 9;
        } else {
            // yPos == t
            // (xPos,yPos) on the left side
            offset = (3*l-2 + (l - (xPos-t) - 1)) % 9;
        }
        total = (total + offset) % 9;
        return (int) (total == 0 ? 9 : total);
    }

    public static void main(String[] args) {
        int num = 4;
        int xPos = 2;
        int yPos = 2;
        Solution sol = new Solution();
        System.out.println("Current index: " + sol.orchestraLayout(num, xPos, yPos));
    }
}