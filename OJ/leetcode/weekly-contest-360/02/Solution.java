import java.util.HashSet;
import java.util.Set;

class Solution {
    public long minimumPossibleSum(int n, int target) {
        Set<Integer> set = new HashSet<>();
        long ans = 0L;
        int num = 1;
        for (int i = 0; i < n; i++) {
            while (set.contains(target - num)) {
                num++;
            }
            ans += num;
            set.add(num);
            num++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        int target = 3;
        System.out.println("test: " + sol.minimumPossibleSum(n, target));
    }
}