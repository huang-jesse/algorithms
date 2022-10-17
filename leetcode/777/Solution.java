class Solution {
    private static final char UPPER_A = 'A';
    private static final char UPPER_X = 'X';
    private static final char UPPER_R = 'R';
    private static final char UPPER_L = 'L';
    public boolean canTransform(String start, String end) {
        int n = start.length();
        if (n == 1) {
            return start.equals(end);
        }
        // check
        int[] counter = new int[26];
        for (int i = 0; i < n; i++) {
            counter[start.charAt(i) - UPPER_A]++;
            counter[end.charAt(i) - UPPER_A]--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        // transform
        int countR = 0;
        for (int i = 0; i < n; i++) {
            char curStart = start.charAt(i);
            char curEnd = end.charAt(i);
            if (curStart == UPPER_R) {
                countR++;
            } else {
                if (curStart != UPPER_X && countR > 0) {
                    // curStart is L, then the R cannot move to the correct position
                    return false;
                }
            }
            if (curEnd == UPPER_R) {
                countR--;
            }
            if (countR < 0) {
                // the R only can move to right direct, so this position is unreachable
                return false;
            }
        }
        int countL = 0;
        for (int i = n - 1; i >= 0; i--) {
            char curStart = start.charAt(i);
            char curEnd = end.charAt(i);
            if (curStart == UPPER_L) {
                countL++;
            } else {
                if (curStart != UPPER_X && countL > 0) {
                    // curStart is R, then the L cannot move to the correct position
                    return false;
                }
            }
            if (curEnd == UPPER_L) {
                countL--;
            }
            if (countL < 0) {
                // the L only can move to left direct, so this position is unreachable
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String start = "X";
        // String end = "R";
        String start = "RXXLRXRXL";
        String end = "XRLXXRRLX";
        System.out.println("test: " + sol.canTransform(start, end));
    }
}