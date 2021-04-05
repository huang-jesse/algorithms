class Solution {
    public int orchestraLayout(int num, int xPos, int yPos) {
        int n = num*num;
        int xMax = num;
        int yMax = num;
        int x = 0;
        int y = 0;
        int cur = 1;
        for (int i = 0; i < xMax; i++) {
            for (int j = 0; j < yMax; j++) {

            }
            System.out.println(cur+" ");
            if (cur == 9) {
                cur = 1;
            } else {
                cur++;
            }
        }
        return 1;
    }

    public static void main(String[] args) {

        // Solution sol = new Solution();
    }
}