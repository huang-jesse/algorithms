class Solution {
    private static final char M = 'M';
    private static final char P = 'P';
    private static final char G = 'G';
    public int garbageCollection(String[] garbage, int[] travel) {
        int n = garbage.length;
        int ans = garbage[0].length();
        int mTravel = 0;
        int pTravel = 0;
        int gTravel = 0;
        int currentTravel = 0;
        for (int i = 1; i < n; i++) {
            ans += garbage[i].length();
            currentTravel += travel[i - 1];
            for (char type : garbage[i].toCharArray()) {
                if (type == M) mTravel = currentTravel;
                if (type == P) pTravel = currentTravel;
                if (type == G) gTravel = currentTravel;
            }
        }
        return ans + mTravel + pTravel + gTravel;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] garbage = {"G","P","GP","GG"};
        int[] travel = {2, 4, 3};
        System.out.println("test: " + sol.garbageCollection(garbage, travel));
    }
}