import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean checkDistances(String s, int[] distance) {
        int n = s.length();
        char[] chars = s.toCharArray();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int cur = chars[i] - 'a';
            if (set.contains(cur)) {
                continue;
            }
            int j = i + 1;
            while (j < n) {
                int next = chars[j] - 'a';
                if (next == cur) {
                    if (j - i - 1 != distance[cur]) {
                        return false;
                    } else {
                        break;
                    }
                }
                j++;
            }
            set.add(cur);
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "abaccb";
        int[] distance = {1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        System.out.println("test: " + sol.checkDistances(s, distance));
    }
}