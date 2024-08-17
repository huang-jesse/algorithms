import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minimumOperationsToMakeKPeriodic(String word, int k) {
        int n = word.length();
        Map<String, Integer> counter = new HashMap<>();
        int max = 0;
        for (int i = 0; i < n; i += k) {
            String sub = word.substring(i, i + k);
            Integer count = counter.merge(sub, 1, Integer::sum);
            max = Math.max(max, count);
        }
        int total = n / k;
        return total - max;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String word = "leetcodeleet";
        int k = 4;
        System.out.println("test: " + sol.minimumOperationsToMakeKPeriodic(word, k));
    }
}