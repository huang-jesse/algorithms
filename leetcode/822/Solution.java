import java.util.HashSet;
import java.util.Set;

class Solution {
    public int flipgame(int[] fronts, int[] backs) {
        int n = fronts.length;
        Set<Integer> invalidSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (fronts[i] == backs[i]) {
                invalidSet.add(fronts[i]);
            }
        }
        int ans = 3000;
        for (int i = 0; i < n; i++) {
            if (!invalidSet.contains(fronts[i])) {
                ans = Math.min(ans, fronts[i]);
            }
            if (!invalidSet.contains(backs[i])) {
                ans = Math.min(ans, backs[i]);
            }
        }
        return ans % 3000;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] fronts = {1,2,4,4,7};
        int[] backs = {1,3,4,1,3};
        System.out.println("test: " + sol.flipgame(fronts, backs));
    }
}