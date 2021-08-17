class Solution {
    public boolean checkRecord(String s) {
        char[] records = s.toCharArray();
        int n = records.length;
        int countAbsent = 0;
        int slow = -1;
        int fast = 0;
        while (fast < n) {
            char cur = records[fast];
            if (cur == 'L') {
                fast++;
            } else {
                if (cur == 'A') {
                    countAbsent++;
                }
                slow = fast;
                fast++;
            }
            
            if (countAbsent >= 2 || fast - slow > 3) {
                // Absent for 2 or more days,
                // Or late for 3 or more consecutive days
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "PPALLA";
        System.out.println("test: " + sol.checkRecord(s));
    }
}