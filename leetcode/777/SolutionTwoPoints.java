class SolutionTwoPoints {
    private static final char UPPER_X = 'X';
    private static final char UPPER_R = 'R';
    private static final char UPPER_L = 'L';
    public boolean canTransform(String start, String end) {
        int n = start.length();
        if (n == 1) {
            return start.equals(end);
        }
        int i = 0;
        int j = 0;
        while (i < n && j < n) {
            while (i < n && start.charAt(i) == UPPER_X) {
                i++;
            }
            while (j < n && end.charAt(j) == UPPER_X) {
                j++;
            }
            if (i == n || j == n) {
                break;
            }
            if (start.charAt(i) != end.charAt(j)) {
                return false;
            } else {
                char cur = start.charAt(i);
                if ((cur == UPPER_L && i < j) || (cur == UPPER_R && i > j)) {
                    return false;
                }
            }
            i++;
            j++;
        }
        while (i < n) {
            if (start.charAt(i) != UPPER_X) {
                return false;
            }
            i++;
        }
        while (j < n) {
            if (end.charAt(j) != UPPER_X) {
                return false;
            }
            j++;
        }
        return true;
    }

    public static void main(String[] args) {
        SolutionTwoPoints sol = new SolutionTwoPoints();
        // String start = "X";
        // String end = "R";
        String start = "RXXLRXRXL";
        String end = "XRLXXRRLX";
        System.out.println("test: " + sol.canTransform(start, end));
    }
}