class Solution {
    private static final String ALICE = "Alice";
    private static final String BOB = "Bob";
    public String losingPlayer(int x, int y) {
        int step = 1;
        while (x >= 1 && y >= 4) {
            x -= 1;
            y -= 4;
            step++;
        }
        return step % 2 == 0 ? ALICE : BOB;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int x = 2;
        int y = 7;
        System.out.println("test: " + sol.losingPlayer(x, y));
    }
}