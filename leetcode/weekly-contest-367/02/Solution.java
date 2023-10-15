import java.util.ArrayList;
import java.util.List;

class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        char[] bits = s.toCharArray();
        for (int i = k; i <= n; i++) {
            int l = 0;
            int r = 0;
            List<String> res = new ArrayList<>();
            int oneBitCount = 0;
            while (l <= n - i) {
                while (r < n && r - l + 1 <= i) {
                    if (bits[r] == '1') {
                        oneBitCount++;
                    }
                    r++;
                }
                if (oneBitCount == k) {
                    String current = s.substring(l, r);
                    res.add(current);
                }

                if (bits[l] == '1') {
                    oneBitCount--;
                }
                l++;
            }
            if (!res.isEmpty()) {
                // find answer
                res.sort((o1, o2) -> o1.compareTo(o2));
                return res.get(0);
            }
        }
        return "";
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "100011001";
        int k = 3;
        System.out.println("test: " + sol.shortestBeautifulSubstring(s, k));
    }
}