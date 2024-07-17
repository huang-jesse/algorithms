class Solution {
    public boolean canChange(String start, String target) {
        int n = start.length();
        int rCount = 0;
        int lCount = 0;
        for (int i = 0; i < n; i++) {
            // R
            if (rCount > 0 && (start.charAt(i) == 'L' || target.charAt(i) == 'L')) {
                return false;
            }
            if (start.charAt(i) == 'R') {
                rCount++;
            }
            if (target.charAt(i) == 'R') {
                rCount--;
            }
            if (rCount < 0) {
                return false;
            }
            // L
            if (lCount > 0 && (start.charAt(n - 1 - i) == 'R' || target.charAt(n - 1 - i) == 'R')) {
                return false;
            }
            if (start.charAt(n - 1 - i) == 'L') {
                lCount++;
            }
            if (target.charAt(n - 1 - i) == 'L') {
                lCount--;
            }
            if (lCount < 0) {
                return false;
            }
        }
        return rCount == 0 && lCount == 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String start  = "_L__R__R_";
        String target = "L______RR";
        // String start  = "R__L";
        // String target = "_LR_";
        // String start  = "_L__R__RL";
        // String target = "L_____RLR";
        System.out.println("test: " + sol.canChange(start, target));
    }
}