class SolutionCounter {
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length())
            return false;
        int n = s.length();
        int[] sCounter = new int[26];
        int[] goalCounter = new int[26];
        int misplaceCounter = 0;
        for (int i = 0; i < n; i++) {
            int sCur = s.charAt(i) - 'a';
            int goalCur = goal.charAt(i) - 'a';
            sCounter[sCur]++;
            goalCounter[goalCur]++;
            if (sCur != goalCur) {
                misplaceCounter++;
            }
        }
        boolean hasSameChar = false;
        for (int i = 0; i < 26; i++) {
            if (sCounter[i] != goalCounter[i]) {
                return false;
            }
            if (goalCounter[i] > 1) {
                hasSameChar = true;
            }
        }
        return misplaceCounter == 2 || (misplaceCounter == 0 && hasSameChar);
    }

    public static void main(String[] args) {
        SolutionCounter sol = new SolutionCounter();
        String s = "dd";
        String goal = "dd";
        // String s = "ab";
        // String goal = "ba";
        // String s = "abcaa";
        // String goal = "abcbb";
        System.out.println("test: " + sol.buddyStrings(s, goal));
    }
}