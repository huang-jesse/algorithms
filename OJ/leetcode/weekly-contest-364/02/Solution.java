import java.util.Arrays;
import java.util.List;

class Solution {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            long res = getSumOfHeights(maxHeights, i);
            ans = Math.max(ans, res);
        }
        return ans;
    }

    private long getSumOfHeights(List<Integer> maxHeights, int summitIndex) {
        int n = maxHeights.size();
        long ans = maxHeights.get(summitIndex);
        // left to right
        int pre = maxHeights.get(summitIndex);
        for (int i = summitIndex + 1; i < n; i++) {
            pre = Math.min(pre, maxHeights.get(i));
            ans += pre;
        }
        // right to left
        pre = maxHeights.get(summitIndex);
        for (int i = summitIndex - 1; i >= 0; i--) {
            pre = Math.min(pre, maxHeights.get(i));
            ans += pre;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<Integer> maxHeights = Arrays.asList(5,3,4,1,1);
        // List<Integer> maxHeights = Arrays.asList(6,5,3,9,2,7);
        System.out.println("test: " + sol.maximumSumOfHeights(maxHeights));
    }
}