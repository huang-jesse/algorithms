class Solution {
    public int lastRemaining(int n) {
        int a1 = 1;
        int numOfRemaind = n;
        int step = 1;
        int times = 1;
        while (numOfRemaind > 1) {
            if (times % 2 == 1) {
                // forward
                a1 = a1 + step;
            } else {
                // backward
                if (numOfRemaind % 2 != 0) {
                    a1 = a1 + step;
                }
            }
            times++;
            numOfRemaind = numOfRemaind >> 1;
            step = step << 1;
        }
        return a1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 9;
        System.out.println("test: " + sol.lastRemaining(n));
    }
}