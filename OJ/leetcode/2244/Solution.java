import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int task : tasks) {
            counter.put(task, counter.getOrDefault(task, 0) + 1);
        }
        int ans = 0;
        for (int count : counter.values()) {
            if (count == 1) return -1;
            ans += count / 3;
            int remaining = count % 3;
            if (remaining == 2 || remaining == 1) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] tasks = {2,2,3,3,2,4,4,4,4,4};
        System.out.println("test: " + sol.minimumRounds(tasks));
    }
}