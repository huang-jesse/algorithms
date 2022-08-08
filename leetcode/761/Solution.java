import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String makeLargestSpecial(String s) {
        int n = s.length();
        if (n <= 2) {
            return s;
        }
        char[] schars = s.toCharArray();
        int count = 0;
        List<String> slist = new ArrayList<>();
        for (int i = 0, j = 0; i < n; i++) {
            if (schars[i] == '1') {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                String specialSubString = "1" + makeLargestSpecial(s.substring(j + 1, i)) + "0";
                slist.add(specialSubString);
                j = i + 1;
            }
        }
        Collections.sort(slist, (a, b) -> b.compareTo(a));
        StringBuilder sb = new StringBuilder();
        for (String str : slist) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "11011000";
        System.out.println("test: " + sol.makeLargestSpecial(s));
    }
}