class BruteForceSearch {
    public int search(String s, String p) {
        int n = s.length();
        int m = p.length();
        if (n < m) {
            return -1;
        }
        int i = 0;
        int j = 0;
        while (i < n) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
            if (j == m) {
                // match
                return i;
            }
        }
        return -1;
    }
}