import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isCovered(int[][] ranges, int left, int right) {
        Set<Integer> coverd = new HashSet<>();
        for (int[] range : ranges) {
            int start = range[0];
            int end = range[1];
            for (int i = start; i <= end; i++) {
                coverd.add(i);
            }
        }
        for (int i = left; i <= right; i++) {
            if (!coverd.contains(i)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] ranges = {{1,2},{3,4},{5,6}};
        int left = 2;
        int right = 5;
        System.out.println("test: " + sol.isCovered(ranges, left, right));
    }
}