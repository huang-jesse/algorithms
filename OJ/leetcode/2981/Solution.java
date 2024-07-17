class Solution {
    public int maximumLength(String s) {
        int n = s.length();
        int low = 0;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low + 1) >> 1);
            if (check(s, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low == 0 ? -1 : low;
    }

    private boolean check(String s, int subLen) {
        if (subLen == 0) return true;
        int n = s.length();
        int[] counter = new int[26];
        int l = 0;
        int r = 0;
        while (r < n) {
            char left = s.charAt(l);
            char right = s.charAt(r);
            if (left == right) {
                if (r - l + 1 >= subLen) {
                    counter[(left - 'a')]++;
                }
                r++;
            } else {
                l = r;
            }
        }
        for (int count : counter) {
            if (count >= 3) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String s = "abcaba";
        // String s = "cccerrrecdcdccedecdcccddeeeddcdcddedccdceeedccecde";
        String s = "aabcaaa";
        System.out.println("test: " + sol.maximumLength(s));
    }
}