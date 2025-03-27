import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minimumSum(int n, int k) {
        int ans = 0;
        int num = 1;
        Set<Integer> set = new HashSet<>();
        while (n > 0) {
            if (!set.contains(k - num)) {
                ans += num;
                n--;
                set.add(num);
            }
            num++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 5;
        int k = 4; // ans = 18
        System.out.println("test: " + sol.minimumSum(n, k));
    }
}