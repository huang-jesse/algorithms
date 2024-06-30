
class Solution {
    public int maxHeightOfTriangle(int red, int blue) {
        return Math.max(triangle(red, blue), triangle(blue, red));
    }

    private int triangle(int first, int second) {
        int firstCurrent = 1;
        int secondCurrent = 2;
        int height = 0;
        while (first > 0 || second > 0) {
            if (first >= firstCurrent) {
                first -= firstCurrent;
                height++;
            } else {
                break;
            }
            if (second >= secondCurrent) {
                second -= secondCurrent;
                height++;
            } else{
                break;
            }
            firstCurrent += 2;
            secondCurrent += 2;
        }
        return height;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int red = 2;
        int blue = 4;
        System.out.println("test: " + sol.maxHeightOfTriangle(red, blue));
    }
}